;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see <https://www.gnu.org/licenses/>.

(ns ironsworn-companion.events
  (:require
   [cljs.reader]
   [reagent.core :refer [atom]]
   [re-frame.core :refer [dispatch]]
   [re-frame.core :refer [reg-event-db reg-event-fx after reg-fx]]
   [clojure.spec.alpha :as s]
   [ironsworn-companion.db :as db]
   [ironsworn-companion.db :as db :refer [app-db]]))

;; nav-history maximum
(def nav-hist-len 20)
;; Initializing the storage backend here
(def ReactNative (js/require "react-native"))

(def AsyncStorage (.-AsyncStorage ReactNative))

;; functions for storage interaction

(def cur-db-id-key "ironsworn-db-id")
(def default-db-id "Unnamed Campaign")

(def all-savegames (atom (sorted-set))) ;; keeping track of the savegames here
(def current-save (atom nil))

(defn set-cur-id [db-id]
  "Sets the default id to db-id. Returns a promise."
  (.setItem AsyncStorage cur-db-id-key db-id))

(defn id-already-present? [db-id]
  "Checks wether a given id is already present. Returns a promise."
  (-> (.getAllKeys AsyncStorage)
      (.then #(contains? % db-id))))

(defn rename-possible? [old-id new-id]
  "Checks wether a rename is possible. Returns a promise."
  (-> (.getAllKeys AsyncStorage)
      (.then #(and (contains? % old-id)
                   (not (contains? % new-id))))))

(declare new-game)

(defn load-db
  ([callback]
   "Gets default db, parses it and feeds it back to caller."
   (-> (.getItem AsyncStorage cur-db-id-key)
       (.then #(when %
                 (load-db callback %))))) ;; if no save is present, create a new one with default name
  ([callback db-id]
   "Gets db item from Storage by id, then parses it and feeds it into callback."
   (-> (.getItem AsyncStorage db-id)
       (.then #(when %
                (-> (set-cur-id db-id)
                    (.then (fn [_] (swap! all-savegames conj db-id)))
                    (.then (fn [_] (reset! current-save db-id)))
                    (.then (fn [_] (cljs.reader/read-string %)))
                    (.then callback)))))))

(defn save-db
  ([db]
   "Save db to Storage under default db-id."
   (-> (.getItem AsyncStorage cur-db-id-key)
       (.then #(when (and % db)
                 (save-db db %)))))
  ([db db-id]
   "Saves db to Storage."
   (-> (.setItem AsyncStorage db-id db)
       (.then (fn [_]
                (swap! all-savegames conj db-id))))))

(defn switch-to-save [db callback new-id]
  "Saves current db and reloads new one."
  (-> (save-db db)
      (.then (fn [_]
               (load-db callback new-id)))))

(defn del-savegame [db-id]
  "Delete savegame with given id from storage."
  (-> (.getItem AsyncStorage cur-db-id-key)
      (.then (fn [cur-id]
               (-> (.removeItem AsyncStorage db-id)
                   (.then (fn [_]
                            (swap! all-savegames disj db-id)))
                   (.then (fn [_]
                            (when (= cur-id db-id)
                              (reset! current-save nil)
                              (.removeItem AsyncStorage cur-db-id-key)))))))))

(defn- rename-save-aux [db-id new-id callback]
  "Rename savegame. No saveguards."
  (-> (.getItem AsyncStorage db-id)
      (.then #(save-db % new-id))
      (.then (fn [_]
               (set-cur-id new-id)))
      (.then (fn [_]
               (del-savegame db-id)))
      (.then (fn [_]
               (.getItem AsyncStorage db-id)))
      (.then (fn [_]
               (load-db callback new-id)))))

(defn rename-save [db-id new-id callback]
  "Rename savegame, if db-id does not already exist."
  (when (and (not (empty? new-id))
             (not= new-id cur-db-id-key))
    (-> (rename-possible? db-id new-id)
        (.then #(when %
                  (rename-save-aux db-id new-id callback))))))

(defn load-all-savegames []
  (let [aux-key-pred (fn [key] (= cur-db-id-key key))]
      (-> (.getAllKeys AsyncStorage)
          (.then #(reset! all-savegames ;; a bit brutal, but works
                          (into (sorted-set)
                                (remove aux-key-pred %)))))))

(defn new-game [new-id callback]
  "Creates a new savegame with new-id, loads it and calls callback on the new db."
  (-> (id-already-present? new-id)
      (.then #(when-not %
                (-> (.setItem AsyncStorage new-id (pr-str app-db))
                    (.then (fn [_]
                             (load-all-savegames))))))))



;; -- Interceptors ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/master/docs/Interceptors.md
;;
(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db [event]]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (throw (ex-info (str "Spec check after " event " failed: " explain-data) explain-data)))))

(def validate-spec
  (if goog.DEBUG
    (after (partial check-and-throw ::db/app-db))
    []))

;; -- Handlers --------------------------------------------------------------

(reg-event-db
 :initialize-db
 validate-spec
 (fn [_ _]
   app-db))

(reg-event-db
 :reload-db
 validate-spec
 (fn [_ [_ db]]
   db))

(reg-fx
 :new-game-in-storage
 (fn [new-id]
   (new-game new-id #(dispatch [:reload-db %]))))

(reg-event-fx
 :new-game
 validate-spec
 (fn [_ [_ new-id]]
   {:new-game-in-storage new-id}))

(reg-fx
 :load-db-from-storage
 (fn [[db db-id]]
   (switch-to-save (pr-str db)
                   #(dispatch [:reload-db %])
                   db-id)))

(reg-event-fx
 :load-db
 validate-spec
 (fn [cofx [_ db-id]]
   {:load-db-from-storage [(:db cofx) db-id]}))

(reg-fx
 :del-save-from-storage
 (fn [db-id]
   (del-savegame db-id)))

(reg-event-fx
 :del-save
 validate-spec
 (fn [_ [_ db-id]]
   {:del-save-from-storage db-id}))

(reg-fx
 :rename-save-storage
 (fn [[old-name new-name]]
   (rename-save old-name new-name
                #(dispatch [:reload-db %]))))

(reg-event-fx
 :rename-save
 validate-spec
 (fn [_ [_ old-name new-name]]
   {:rename-save-storage [old-name new-name]}))

(reg-fx ;;effect handler for saving to storage
 :save-to-storage
 (fn [db]
   (save-db db)))

(reg-event-fx
 :save-db ;; only fires save-to-storage handler with current db
 (fn [cofx [_]]
   {:save-to-storage (pr-str (:db cofx))}))

(reg-event-db
 :insert-journal-entry
 validate-spec
 (fn [db [_ value]]
   (update-in db [:journal] conj value)))

(reg-event-db
 :set-screen
 validate-spec
 (fn [db [_ value]]
   (-> db
       (update-in [:nav-history] conj (:active-screen db))
       (update-in [:nav-history] #(apply list (take nav-hist-len %)))
       (assoc-in [:active-screen] value))))

(reg-event-db
 :go-back
 validate-spec
 (fn [db _]
   (if (empty? (:nav-history db))
     db
     (-> db
         (assoc :active-screen (first (:nav-history db)))
         (update-in [:nav-history] rest)))))

(reg-event-db
 :mod-stat
 validate-spec
 (fn [db [_ [char-name stat-name value]]]
   (update-in db
              [:characters char-name :stats stat-name]
              db/mod-stat value)))

(reg-event-db
 :mod-res
 validate-spec
 (fn [db [_ [char-name res-name value]]]
   (update-in db
              [:characters char-name :resources res-name]
              db/mod-res value)))

(reg-event-db
 :set-ini
 validate-spec
 (fn [db [_ [char-name value]]]
   (assoc-in db
              [:characters char-name :initiative]
              value)))

(reg-event-db
 :set-debility
 validate-spec
 (fn [db [_ [char-name deb-name value]]]
   (assoc-in db
             [:characters char-name :debilities deb-name]
             value)))

(reg-event-db
 :mod-momentum
 validate-spec
 (fn [db [_ [char-name value]]]
   (let [debilities (get-in db [:characters char-name :debilities])]
     (update-in db
                [:characters char-name :momentum]
                db/mod-momentum value debilities))))

(reg-event-db
 :reset-momentum
 validate-spec
 (fn [db [_ char-name]]
   (let [debilities (get-in db [:characters char-name :debilities])]
     (update-in db
                [:characters char-name :momentum]
                db/reset-momentum debilities))))

(reg-event-db
 :mod-progress
 validate-spec
 (fn [db [_ [pt-name value] & {:keys [location char-name]}]]
   (case location
     :progress-tracks (update-in db
                                 [:progress-tracks pt-name]
                                 db/mod-progress value)
     :vows (update-in db
                      [:characters char-name :vows pt-name]
                      db/mod-progress value))))

(reg-event-db
 :mod-progress-lvl
 validate-spec
 (fn [db [_ [pt-name lvl] & {:keys [location char-name]}]]
   (case location
     :progress-tracks (assoc-in db
                                [:progress-tracks pt-name 0]
                                lvl)
     :vows (assoc-in db
                     [:characters char-name :vows pt-name 0]
                     lvl))))

(reg-event-db
 :insert-new-pt
 validate-spec
 (fn [db [_ pt-name & {:keys [location char-name rank]
                       :or {location :progress-tracks
                            char-name nil
                            rank "Dangerous"}}]]
   (case location
     :progress-tracks (assoc-in db
                                [:progress-tracks pt-name]
                                [rank 0])
     :vows (assoc-in db
                     [:characters char-name :vows pt-name]
                     [rank 0]))))

(reg-event-db
 :delete-prog
 validate-spec
 (fn [db [_ pt-name & {:keys [location char-name]}]]
   (case location
     :progress-tracks (update db :progress-tracks
                              #(dissoc % pt-name))
     :vows (update-in db [:characters char-name :vows]
                      #(dissoc % pt-name)))))

(reg-event-db
 :mod-bonds
 validate-spec
 (fn [db [_ [char-name value]]]
   (update-in db
              [:characters char-name :bonds]
              db/mod-bond-ticks value)))

(reg-event-fx
 :insert-new-char
 validate-spec
 (fn [cofx [_ char-name]]
   {:db (assoc-in (:db cofx)
                  [:characters char-name]
                  (assoc db/new-char :name char-name))
    :dispatch [:set-active-char char-name]}))

(reg-event-fx
 :delete-char
 validate-spec
 (fn [cofx [_ char-name]]
   {:db (update (:db cofx) 
                :characters
                #(dissoc % char-name))
    :dispatch [:cleanup-active-char char-name]}))

(reg-event-db
 :set-active-move
 validate-spec
 (fn [db [_ move]]
   (assoc db
           :active-move
           move)))

(reg-event-db
 :cleanup-active-char
 validate-spec
 (fn [db [_ char-name]]
   (if (= char-name (:active-char db))
     (assoc db
            :active-char
            (first (keys (:characters db))))
     db)))

(reg-event-db
 :set-active-char
 validate-spec
 (fn [db [_ char-name]]
   (assoc db
          :active-char
          char-name)))

(reg-event-db
 :change-asset-note
 validate-spec
 (fn [db [_ char-name asset new-note]]
   (assoc-in db [:characters char-name :assets]
             (db/update-val-in-coll
              (get-in db [:characters char-name :assets])
              asset
              (assoc-in asset [:custom-note 1]
                        new-note)))))

(reg-event-db
 :toggle-perk
 validate-spec
 (fn [db [_ char-name asset perk]]
   (let [char-assets (get-in db [:characters char-name :assets])]
     (assoc-in db [:characters char-name :assets]
               (db/update-val-in-coll char-assets
                                     asset
                                     (assoc asset :perks
                                            (db/update-val-in-coll
                                             (:perks asset)
                                             perk
                                             (update perk :enabled not))))))))

(reg-event-db
 :mod-asset-resource
 validate-spec
 (fn [db [_ char-name asset value]]
   (let [char-assets (get-in db [:characters char-name :assets])]
     (assoc-in db [:characters char-name :assets]
               (db/update-val-in-coll char-assets
                                      asset
                                      (db/mod-asset-res asset value))))))

(reg-event-db
 :add-ass-to-act-char
 validate-spec
 (fn [db [_ asset-name]]
   (let [act-char (:active-char db)
         asset (first (filter
                       #(= asset-name (:name %))
                       (:assets db)))]
     (if act-char
       (update-in db [:characters act-char :assets]
                  conj asset)
       db))))

(reg-event-db
 :rm-ass
 validate-spec
 (fn [db [_ char-name asset-name]]
   (update-in db [:characters char-name :assets]
              (partial remove 
               #(= asset-name (:name %))))))

(reg-event-db
 :mod-xp
 validate-spec
 (fn [db [_ char-name value]]
   (update-in db [:characters char-name :xp]
              #(max 0 (+ % value)))))

(reg-event-db
 :set-theme
 validate-spec
 (fn [db [_ topic theme-name]]
   (let [old-world (:world db)]
     (assoc db :world
            (map #(if (= % topic)
                    (assoc topic :selected-val theme-name)
                    %)
                 old-world)))))

(reg-event-db
 :add-theme
 validate-spec
 (fn [db [_ topic new-theme]]
   (if (and (contains? new-theme :key)
            (contains? new-theme :description)
            (contains? new-theme :starter))
     (let [old-world (:world db)]
       (assoc db :world
              (map #(if (= % topic)
                      (assoc-in topic [:themes (:key new-theme)]
                                [(:description new-theme)
                                 (:starter new-theme)])
                      %)
                   old-world)))
     db)))

(reg-event-db
 :del-theme
 validate-spec
 (fn [db [_ topic]]
   (let [old-world (:world db)]
     (assoc db :world
            (map #(if (= % topic)
                    (let [new-top (update topic :themes
                                          dissoc (:selected-val topic))]
                      (assoc new-top :selected-val (first (keys (:themes new-top)))))
                    %)
                 old-world)))))

(reg-event-db
 :change-location
 validate-spec
 (fn [db [_ r-name name new-name new-desc]]
   (if (and (not-empty new-name)
            (not-empty new-desc))
     (-> db
         (update-in [:regions r-name :locations] dissoc name)
         (assoc-in [:regions r-name :locations new-name] new-desc))
     db)))

(reg-event-db
 :new-location
 validate-spec
 (fn [db [_ r-name]]
   (assoc-in db [:regions r-name :locations "New location"] "Describe it")))

(reg-event-db
 :del-location
 validate-spec
 (fn [db [_ r-name l-name]]
   (update-in db [:regions r-name :locations] dissoc l-name)))

(reg-event-db
 :mod-bond-detail
 validate-spec
 (fn [db [_ old-name new-name desc]]
   (let [old-details (:bond-details db)]
     (assoc db :bond-details
            (conj (filter #(not= (:name %) old-name) old-details)
                  {:name new-name :description desc})))))

(reg-event-db
 :add-bond-detail
 validate-spec
 (fn [db [_ name desc]]
   (update db :bond-details
           conj {:name name :description desc})))

(reg-event-db
 :del-bond-detail
 validate-spec
 (fn [db [_ name]]
   (update db :bond-details
           (fn [details]
             (filter #(not= name (:name %)) details)))))

(reg-event-db
 :mod-journal-entry
 validate-spec
 (fn [db [_ old-entry new-entry]]
   (let [update-if-entry (fn [entry] (if (= old-entry entry)
                                       new-entry
                                       entry))]
     (update db :journal
             #(map update-if-entry %)))))

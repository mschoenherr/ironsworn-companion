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
   [re-frame.core :refer [reg-event-db reg-event-fx after]]
   [clojure.spec.alpha :as s]
   [ironsworn-companion.db :as db]
   [ironsworn-companion.db :as db :refer [app-db]]))

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
 :insert-journal-entry
 validate-spec
 (fn [db [_ value]]
   (update-in db [:journal] conj value)))

(reg-event-db
 :set-screen
 validate-spec
 (fn [db [_ value]]
   (update-in db [:nav-history] conj (:active-screen db))
   (assoc-in db [:active-screen] value)))

(reg-event-db
 :go-back
 validate-spec
 (fn [db _]
   (assoc db :active-screen (first (:nav-history db)))
   (update-in db [:nav-history] pop)))

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
 (fn [db [_ pt-name & {:keys [location char-name]
                       :or {location :progress-tracks
                            char-name nil}}]]
   (case location
     :progress-tracks (assoc-in db
                                [:progress-tracks pt-name]
                                ["Dangerous" 0])
     :vows (assoc-in db
                     [:characters char-name :vows pt-name]
                     ["Dangerous" 0]))))

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

(reg-event-db
 :delete-char
 validate-spec
 (fn [db [_ char-name]]
   (update db
           :characters
           #(dissoc % char-name))))

(reg-event-db
 :set-active-move
 validate-spec
 (fn [db [_ move]]
   (assoc db
           :active-move
           move)))

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
     (update-in db [:characters act-char :assets]
                conj asset))))

(reg-event-db
 :rm-ass
 validate-spec
 (fn [db [_ char-name asset-name]]
   (update-in db [:characters char-name :assets]
              (partial remove 
               #(= asset-name (:name %))))))

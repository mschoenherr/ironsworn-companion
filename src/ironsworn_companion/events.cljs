(ns ironsworn-companion.events
  (:require
   [re-frame.core :refer [reg-event-db after]]
   [clojure.spec.alpha :as s]
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

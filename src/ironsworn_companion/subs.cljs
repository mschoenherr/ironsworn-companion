(ns ironsworn-companion.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-journal
  (fn [db _]
    (:journal db)))

(reg-sub
 :get-active-char
 (fn [db _]
   (let [active-char-key (:active-char db)]
     (get-in db [:characters active-char-key ]))))

(reg-sub
 :get-chars
 (fn [db _]
   (:characters db)))

(reg-sub
 :get-char
 (fn [db [_ name]]
   (get-in db [:characters name])))

(reg-sub
 :get-roll-result
 (fn [db _]
   (:roll-result db)))

(reg-sub
 :get-active-screen
 (fn [db _]
   (:active-screen db)))

(reg-sub
 :get-oracle
 (fn [db _]
   (:oracle db)))

(reg-sub
 :get-progress-tracks
 (fn [db _]
   (:progress-tracks db)))

(reg-sub
 :get-moves
 (fn [db _]
   (:moves db)))

(reg-sub
 :get-active-move
 (fn [db _]
   (:active-move db)))

(reg-sub
 :get-active-vows
 (fn [db _]
   (let [act-char-key (:active-char db)]
     (get-in db [:characters act-char-key :vows]))))

(reg-sub
 :get-all-assets
 (fn [db _]
   (:assets db)))

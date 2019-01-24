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

(ns ironsworn-companion.android.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [ironsworn-companion.db :refer [app-db]]
            [ironsworn-companion.views :as views]
            [ironsworn-companion.events :refer [load-db save-db load-all-savegames]]
            [ironsworn-companion.subs]))

(def ReactNative (js/require "react-native"))
(def app-state (.-AppState ReactNative))
(def app-registry (.-AppRegistry ReactNative))
(def view (r/adapt-react-class (.-View ReactNative)))
(def drawer (r/adapt-react-class (.-DrawerLayoutAndroid ReactNative)))

;; Set the entry point for app-root
(defn app-root []
  [drawer {:flex 1
           :drawer-width 180
           :render-navigation-view (fn [_]
                                     (r/as-element [views/nav-menu]))} ;; r/as-element needed to cast nav-menu as native react-native component
   [views/main-screen]])

(defn init []
  (dispatch-sync [:initialize-db])
  (.addEventListener app-state "change"
                     (fn [next-state]
                       (case next-state
                         "active" (load-db
                                   (fn [db]
                                     (dispatch-sync [:reload-db db])))
                         (dispatch-sync [:save-db]))))
  (.registerComponent app-registry "IronswornCompanion" #(r/reactify-component app-root)))

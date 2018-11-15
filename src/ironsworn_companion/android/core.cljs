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
            [ironsworn-companion.views :as views]
            [ironsworn-companion.events]
            [ironsworn-companion.subs]))

(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))
(def view (r/adapt-react-class (.-View ReactNative)))
(def tool-bar (r/adapt-react-class (.-ToolbarAndroid ReactNative)))

;; Set the entry point for app-root
(defn app-root []
  [view {:style {:flex 1}}
   [tool-bar {:title "Ironsworn" :style {:height 64}
              :actions [{:title "Journal"}
                        {:title "Character"}
                        {:title "Progress Tracks"}
                        {:title "Moves"}
                        {:title "Assets"}]
              :on-action-selected #(case %
                                         0 (dispatch [:set-screen :journal])
                                         1 (dispatch [:set-screen :chars])
                                         2 (dispatch [:set-screen :progress-tracks])
                                         3 (dispatch [:set-screen :move-list])
                                         4 (dispatch [:set-screen :asset-list]))}]
   [views/choose-screen]])

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "IronswornCompanion" #(r/reactify-component app-root)))

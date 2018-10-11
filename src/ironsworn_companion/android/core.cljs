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
                        {:title "Moves"}]
              :on-action-selected #(case %
                                         0 (dispatch [:set-screen :journal])
                                         1 (dispatch [:set-screen :chars])
                                         2 (dispatch [:set-screen :progress-tracks])
                                         3 (dispatch [:set-screen :move-list]))}]
   [views/choose-screen]])

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "IronswornCompanion" #(r/reactify-component app-root)))

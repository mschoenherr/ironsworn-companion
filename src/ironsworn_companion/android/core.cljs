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
  [view
   [tool-bar {:title "Ironsworn" :height 64 :actions [{:title "Journal"} {:title "Character"}]
              :on-action-selected #(case %
                                         0 (dispatch [:set-screen :journal])
                                         1 (dispatch [:set-screen :chars]))}]
   [views/choose-screen]])

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "IronswornCompanion" #(r/reactify-component app-root)))

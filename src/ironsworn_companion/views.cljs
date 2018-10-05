(ns ironsworn-companion.views
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [ironsworn-companion.events]
            [ironsworn-companion.subs]))

(def ReactNative (js/require "react-native"))

(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))

(defn- sorted-hash-seq [m]
  "Returns a (seq m) sorted by first."
  (sort-by first (seq m)))

(defn journal-view []
  "View for reading journal and inserting new entries."
  (let [journal (subscribe [:get-journal])]
    [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
     [text-input {:on-submit-editing #(dispatch [:insert-journal-entry (.. % -nativeEvent -text)]) :width 128 :placeholder "Adventure awaits!"}]
     [scroll-view
      (for [entry @journal]
        ^{:key entry}
        [text entry])]]))

(defn stat-view [char-name [stat-name stat-value]]
  "Component for rendering and updating stats."
  [view
   [text stat-name]
   [text stat-value]
   [button {:title "+" :on-press #(dispatch [:mod-stat
                                             [char-name
                                              stat-name
                                              1]])}]
   [button {:title "-" :on-press #(dispatch [:mod-stat
                                             [char-name
                                              stat-name
                                              -1]])}]])

(defn res-view [char-name [res-name res-value]]
  "Component for rendering and updating resources."
  [view
   [text res-name]
   [text res-value]
   [button {:title "+" :on-press #(dispatch [:mod-res
                                             [char-name
                                              res-name
                                              1]])}]
   [button {:title "-" :on-press #(dispatch [:mod-res
                                             [char-name
                                              res-name
                                              -1]])}]])

(defn char-view [name]
  "Component for viewing and editing a char identified by name."
  (let [char (subscribe [:get-char name])]
    [view
     [text (:name @char)]
     (for [stat (sorted-hash-seq (:stats @char))]
       ^{:key stat}
       [stat-view name stat])
     (for [res (sorted-hash-seq (:resources @char))]
       ^{:key res}
       [res-view name res])]))

(defn chars-view []
  "Component for viewing all chars in db."
  (let [chars (subscribe [:get-chars])]
    [view
     (for [char-name (keys @chars)]
       ^{:key char-name}
       [char-view char-name])]))

(defn choose-screen []
  "High-level view that simply invokes the component corresponding to ::active-screen."
  (let [active-screen (subscribe [:get-active-screen])]
    (case @active-screen
      :journal [journal-view]
      :chars [chars-view]
      :default [text "hi"])))



(ns ironsworn-companion.views
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [ironsworn-companion.db :as db]
            [ironsworn-companion.events]
            [ironsworn-companion.subs]))

(def ReactNative (js/require "react-native"))

(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def switch-comp (r/adapt-react-class (.-Switch ReactNative)))
(def picker (r/adapt-react-class (.-Picker ReactNative)))
(def picker-item (r/adapt-react-class (.. ReactNative -Picker -Item)))

(defn- sorted-hash-seq [m]
  "Returns a (seq m) sorted by first."
  (sort-by first (seq m)))

;; Views for the Journal
(defn journal-view []
  "View for reading journal and inserting new entries."
  (let [journal (subscribe [:get-journal])]
    [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
     [text-input {:on-submit-editing #(dispatch [:insert-journal-entry (.. % -nativeEvent -text)]) :width 128 :placeholder "Adventure awaits!"}]
     [scroll-view
      (for [entry @journal]
        ^{:key entry}
        [text entry])]]))

;; Views for Char
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

(defn ini-view [char-name value]
  "Component for rendering initiative for given char."
  [view
   [text "Initiative"]
   [switch-comp {:value value :on-value-change #(dispatch [:set-ini
                                                           [char-name %]])}]])

(defn debility-view [char-name [deb-name value]]
  "Component for rendering debility for given char."
  [view
   [text deb-name]
   [switch-comp {:value value :on-value-change #(dispatch [:set-debility
                                                           [char-name deb-name %]])}]])

(defn momentum-view [char-name value]
  "Component for rendering momentum for a given char."
  [view
   [text "Momentum"]
   [text value]
   [button {:title "+" :on-press #(dispatch [:mod-momentum
                                             [char-name
                                              1]])}]
   [button {:title "-" :on-press #(dispatch [:mod-momentum
                                             [char-name
                                              -1]])}]
   [button {:title "Reset" :on-press #(dispatch [:reset-momentum
                                                 char-name])}]])

(defn progress-view [[name [lvl ticks]] & {:keys [location char-name]
                                           :or {location :progress-tracks
                                                char-name nil}}]
  "Component for viewing progress track."
  (let [delete-prog? (atom false)]
    (fn [[name [lvl ticks]] & {:keys [location char-name]
                               :or {location :progress-tracks
                                    char-name nil}}]
     [view
      [text name]
      (if @delete-prog?
        [button {:title "Really delete?" :on-press #(dispatch [:delete-prog name :location location :char-name char-name])}]
        [button {:title "Delete" :on-press #(swap! delete-prog? not)}])
      [picker {:selected-value lvl
               :on-value-change (fn [val index]
                                  (dispatch [:mod-progress-lvl [name val] :location location
                                             :char-name char-name]))}
       (for [level (sort db/levels)]
         ^{:key level}
         [picker-item {:label level :value level}])]
      [button {:title "-" :on-press #(dispatch [:mod-progress
                                                [name -1]
                                                :location location :char-name char-name])}]
      [text ticks]
      [button {:title "+" :on-press #(dispatch [:mod-progress
                                                [name 1]
                                                :location location :char-name char-name])}]])))

(defn vow-view [char-name [vow-name [lvl ticks]]]
  "Component for rendering vows."
  [progress-view [vow-name [lvl ticks]] :location :vows :char-name char-name])

(defn bonds-view [char-name ticks]
  "Component for rendering bonds."
  [view
   [text "Bonds"]
   [button {:title "-" :on-press #(dispatch [:mod-bonds
                                             [char-name
                                              -1]])}]
   [text ticks]
   [button {:title "+" :on-press #(dispatch [:mod-bonds
                                             [char-name
                                              1]])}]])


(defn char-view [name]
  "Component for viewing and editing a char identified by name."
  (let [char (subscribe [:get-char name])]
    [view
     [text (:name @char)]
     [ini-view name (:initiative @char)]
     [momentum-view name (:momentum @char)]
     (for [stat (sorted-hash-seq (:stats @char))]
       ^{:key stat}
       [stat-view name stat])
     (for [res (sorted-hash-seq (:resources @char))]
       ^{:key res}
       [res-view name res])
     (for [deb (sorted-hash-seq (:debilities @char))]
       ^{:key deb}
       [debility-view name deb])
     (for [vow (sorted-hash-seq (:vows @char))]
       ^{:key vow}
       [vow-view name vow])
     [text-input {:on-submit-editing #(do
                                        (dispatch [:insert-new-pt (.. % -nativeEvent -text)
                                                   :location :vows :char-name name]))
                  :width 128 :placeholder "New bond name"}]
     [bonds-view name (:bonds @char)]]))

(defn chars-view []
  "Component for viewing all chars in db."
  (let [chars (subscribe [:get-chars])
        input-new-char? (atom false)
        delete-char? (atom false)]
    (fn []
      [view {:style {:flex 7}}
      [scroll-view 
       (for [char-name (keys @chars)]
         ^{:key char-name}
         [char-view char-name])
       (if @input-new-char?
         [text-input {:on-submit-editing #(do
                                            (dispatch [:insert-new-char (.. % -nativeEvent -text)])
                                            (swap! input-new-char? not))
                      :width 128 :placeholder "Character Name"}]
         [button {:title "New Character" :on-press #(swap! input-new-char? not)}])
       (if @delete-char?
         [text-input {:on-submit-editing #(do
                                            (dispatch [:delete-char (.. % -nativeEvent -text)])
                                            (swap! delete-char? not))
                      :width 128
                      :placeholder "Type name of character to delete"}]
         [button {:title "Delete Character" :on-press #(swap! delete-char? not)}])]])))



(defn progress-tracks-view []
  "Component for viewing all progress-tracks."
  (let [pts (subscribe [:get-progress-tracks])
        input-new-pt? (atom false)]
    (fn []
      [view {:style {:flex 7}}
       (for [pt (sorted-hash-seq @pts)]
         ^{:key pt}
         [progress-view pt])
       (if @input-new-pt?
         [text-input {:on-submit-editing #(do
                                            (dispatch [:insert-new-pt (.. % -nativeEvent -text)])
                                            (swap! input-new-pt? not))
                      :width 128 :placeholder "Name"}]
         [button {:title "New" :on-press #(swap! input-new-pt? not)}])])))

(defn move-link [move]
  "Component for viewing the link to a particular move."
  [view
   [button {:title (:name move) :on-press #(do
                                             (dispatch [:set-active-move move])
                                             (dispatch [:set-screen :move]))}]])

(defn move-view []
  "Component for viewing the active move and rolling on it."
  (let [move (subscribe [:get-active-move])]
    [view
     [text (:name @move)]
     [text (:description @move)]]))

(defn moves-list []
  "Component for viewing all moves."
  (let [moves (subscribe [:get-moves])]
    [scroll-view {:style {:flex 7}}
     (for [move @moves]
       ^{:key (:name move)}
       [move-link move])]))

;; Nav-views
(defn choose-screen []
  "High-level view that simply invokes the component corresponding to ::active-screen."
  (let [active-screen (subscribe [:get-active-screen])]
    (case @active-screen
      :journal [journal-view]
      :chars [chars-view]
      :progress-tracks [progress-tracks-view]
      :move-list [moves-list]
      :move [move-view]
      :default [text "hi"])))



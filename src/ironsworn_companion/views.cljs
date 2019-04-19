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

(ns ironsworn-companion.views
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [ironsworn-companion.about :refer [about-text software-license-text rpg-license-text]]
            [ironsworn-companion.rolls :as rolls]
            [ironsworn-companion.db :as db]
            [ironsworn-companion.events :refer [all-savegames current-save]]
            [ironsworn-companion.subs]))

;; react native imports for use in re-frame
(def ReactNative (js/require "react-native"))

(def react-native-text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def react-text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def react-button (r/adapt-react-class (.-Button ReactNative)))
(def switch-comp (r/adapt-react-class (.-Switch ReactNative)))
(def picker (r/adapt-react-class (.-Picker ReactNative)))
(def picker-item (r/adapt-react-class (.. ReactNative -Picker -Item)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def Toast (.-ToastAndroid ReactNative))

;; static images go here
(def zerotick (js/require "./images/zerotick.png"))
(def onetick (js/require "./images/onetick.png"))
(def twotick (js/require "./images/twotick.png"))
(def threetick (js/require "./images/threetick.png"))
(def fourtick (js/require "./images/fourtick.png"))
(def d6-pics {0 (js/require "./images/d6-0.png")
              1 (js/require "./images/d6-1.png")
              2 (js/require "./images/d6-2.png")
              3 (js/require "./images/d6-3.png")
              4 (js/require "./images/d6-4.png")
              5 (js/require "./images/d6-5.png")
              6 (js/require "./images/d6-6.png")})

(def d10-pics {0 (js/require "./images/d10-0.png")
               1 (js/require "./images/d10-1.png")
               2 (js/require "./images/d10-2.png")
               3 (js/require "./images/d10-3.png")
               4 (js/require "./images/d10-4.png")
               5 (js/require "./images/d10-5.png")
               6 (js/require "./images/d10-6.png")
               7 (js/require "./images/d10-7.png")
               8 (js/require "./images/d10-8.png")
               9 (js/require "./images/d10-9.png")
               10 (js/require "./images/d10-10.png")})

;; helper function for reliable order of output
(defn- sorted-hash-seq [m]
  "Returns a (seq m) sorted by first."
  (sort-by first (seq m)))

;; atomic views and constants

(def ironsworn-color "#8ba4a8")

(defn toast [text]
  "Displays text as a short toast."
  (.show Toast text (.-SHORT Toast)))

(defn text
  ([display-text]
   [react-native-text {:style {:font-size 16
                               :text-align "center"}}
    display-text])
  ([{:keys [:style]} display-text]
   "Generic text-view for uniform styling."
   (let [default-style {:font-size 16
                        :text-align "center"}]
     [react-native-text {:style (merge default-style style)}
      display-text])))

(defn heading-view [heading-text]
  "Shows a heading for a screen."
  [text
   {:style {:font-weight "bold"
            :font-size 20
            :color ironsworn-color
            :width "100%"
            :border-width 2
            :border-radius 2
            :border-color ironsworn-color
            :padding 4}}
   heading-text])

(defn subheading-view [heading-text]
  "Shows a subheading."
  [text {:style {:font-weight "bold"
                 :font-size 18
                 :text-align "center"
                 :color ironsworn-color}}
   heading-text])

(defn button [style-map & {:keys [enclosing-style] ;; could be more readible with sensible destucturing
                           :or {enclosing-style {}}}]
  "Default Button for the app, merges styles in style-map mit defaults. Defaults having precedence."
  [view {:style (merge enclosing-style {:padding 2})}
   [react-button
    (merge
     style-map
     {:color ironsworn-color})]])

(defn text-input [{:keys [:on-submit-editing :placeholder
                          :style :on-change-text
                          :default-value]
                   :or {style {:width "100%"}}}]
  "Default text-input for the app."
  [react-text-input (merge
                     {:select-text-on-focus true
                      :style style}
                     (if default-value
                       {:default-value default-value}
                       {:placeholder placeholder})
                     (if on-submit-editing
                       {:on-submit-editing #(on-submit-editing (.. % -nativeEvent -text))}
                       {:on-change-text #(on-change-text %)}))])

(defn text-list-view [heading text-coll]
  "Component for viewing a list of strings."
  [view {:style {:align-items "center"}}
   [subheading-view heading]
   (for [item text-coll]
     ^{:key item}
     [text {:style {:border-bottom-width 1
                    :margin 1}}
      item])])

;; Views for the Journal
(defn journal-entry-view [entry]
  "Component for viewing and editing a single entry to the journal."
  (let [edit? (atom false)]
    (fn [entry]
      [view {:flex-direction "row"
             :padding 1
             :justify-content "space-between"}
       (if @edit?
         [text-input {:on-submit-editing #(dispatch [:mod-journal-entry entry %])
                      :default-value entry}]
         [text {:style {:margin 2
                        :width "80%"
                        :padding 1}} entry])
       [button {:title "Edit"
                :on-press #(swap! edit? not)}]])))

(defn journal-view []
  "View for reading journal and inserting new entries."
  (let [journal (subscribe [:get-journal])]
    [view {:style {:flex 7
                   :width "100%"
                   :flex-direction "column"
                   :align-items "center"}}
     [heading-view "Journal"]
     [text-input {:on-submit-editing #(dispatch [:insert-journal-entry %])
                  :placeholder "What's up?"}]
     [scroll-view
      (for [entry @journal]
        ^{:key entry}
        [journal-entry-view entry])]]))

;; Views for Char
(defn stat-view [char-name [stat-name stat-value]]
  "Component for rendering and updating stats."
  [view
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"}}
    [text stat-name]
    [text {:style {:padding 5}}
     stat-value]]
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"}}
    [button {:title "-" :on-press #(dispatch [:mod-stat
                                              [char-name
                                               stat-name
                                               -1]])}
     :enclosing-style {:width 48 :height 48}]
    [button {:title "+" :on-press #(dispatch [:mod-stat
                                              [char-name
                                               stat-name
                                               1]])}
     :enclosing-style {:width 48 :height 48}]]])

(defn res-view [char-name [res-name res-value]]
  "Component for rendering and updating resources."
  [view
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"}}
    [text res-name]
    [text {:style {:padding 5}}
     res-value]]
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"}}
    [button {:title "-" :on-press #(dispatch [:mod-res
                                              [char-name
                                               res-name
                                               -1]])}
     :enclosing-style {:width 48 :height 48}]
    [button {:title "+" :on-press #(dispatch [:mod-res
                                              [char-name
                                               res-name
                                               1]])}
     :enclosing-style {:width 48 :height 48}]]])

(defn ini-view [char-name value]
  "Component for rendering initiative for given char."
  [view
   [text "Initiative"]
   [switch-comp {:value value :on-value-change #(dispatch [:set-ini
                                                           [char-name %]])}]])

(defn debility-view [char-name [deb-name value]]
  "Component for rendering debility for given char."
  [view {:style {:margin 1}}
   [text deb-name]
   [switch-comp {:value value :on-value-change #(dispatch [:set-debility
                                                           [char-name deb-name %]])}]])

(defn momentum-view [char-name value]
  "Component for rendering momentum for a given char."
  [view {:style {:border-width 1
                 :margin 1}}
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"
                  :margin 5}}
    [text {:style {:padding 5}}
     "Momentum"]
    [button {:title "Reset"
             :on-press #(dispatch [:reset-momentum
                                   char-name])}]]
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"}}
    [button {:title "-" :on-press #(dispatch [:mod-momentum
                                              [char-name
                                               -1]])}
     :enclosing-style {:width 48 :height 48}]
    [text value]
    [button {:title "+" :on-press #(dispatch [:mod-momentum
                                              [char-name
                                               1]])}
     :enclosing-style {:width 48 :height 48}]]])

(defn progress-bar-view [ticks]
  "Component for viewing the ticks in a progress bar."
  [view {:style {:flex-direction "row"}}
   (for [ind (take 10 (range))]
     ^{:key ind}
     [image {:style (merge {:width 32
                            :height 32
                            :margin 1}
                           (case ind
                             4 {:margin-right 7}
                             {}))
             :source
             (let [rem-ticks (- ticks (* 4 ind))]
               (cond
                 (<= rem-ticks 0) zerotick
                 (= 1 rem-ticks) onetick
                 (= 2 rem-ticks) twotick
                 (= 3 rem-ticks) threetick
                 :else fourtick))}])])

(defn progress-view [[name [lvl ticks]] & {:keys [location char-name]
                                           :or {location :progress-tracks
                                                char-name nil}}]
  "Component for viewing progress track."
  (let [delete-prog? (atom false)]
    (fn [[name [lvl ticks]] & {:keys [location char-name]
                               :or {location :progress-tracks
                                    char-name nil}}]
      [view {:style {:border-width 1
                     :margin 1
                     :padding 2}}
       [view {:style {:flex-direction "row"
                      :justify-content "space-evenly"
                      :border-bottom-width 1}}
        [text name]
        (if @delete-prog?
          [button {:title "Really delete?" :on-press #(dispatch [:delete-prog name :location location :char-name char-name])}]
          [button {:title "Delete" :on-press #(swap! delete-prog? not)}])]
       [view {:style {:flex-direction "row"
                      :flex-wrap "wrap"
                      :justify-content "space-evenly"}}
        [picker {:style {:flex 1}
                 :selected-value lvl
                 :on-value-change (fn [val index]
                                    (dispatch [:mod-progress-lvl [name val] :location location
                                               :char-name char-name]))}
         (for [level db/levels]
           ^{:key level}
           [picker-item {:label level :value level}])]
        [button {:title "-" :on-press #(dispatch [:mod-progress
                                                  [name -1]
                                                  :location location :char-name char-name])}
         :enclosing-style {:width 48 :height 48}]
        [button {:title "+" :on-press #(dispatch [:mod-progress
                                                  [name 1]
                                                  :location location :char-name char-name])}
         :enclosing-style {:width 48 :height 48}]]
       [progress-bar-view ticks]])))

(defn vow-view [char-name [vow-name [lvl ticks]]]
  "Component for rendering vows."
  [progress-view [vow-name [lvl ticks]] :location :vows :char-name char-name])

(defn bonds-view [char-name ticks]
  "Component for rendering bonds."
  [view {:style {:border-width 1
                 :margin 1}}
   [view {:style {:flex-direction "row"
                  :justify-content "space-evenly"}}
    [subheading-view "Bonds"]
    [button {:title "-" :on-press #(dispatch [:mod-bonds
                                              [char-name
                                               -1]])}
     :enclosing-style {:width 48 :height 48}]
    [button {:title "+" :on-press #(dispatch [:mod-bonds
                                              [char-name
                                               1]])}
     :enclosing-style {:width 48 :height 48}]]
   [progress-bar-view ticks]])

(defn stats-view [name stats]
  "Component for viewing and changing stats."
  [view {:style {:border-width 1
                 :margin 1}}
   [subheading-view "Stats"]
   [view {:style {:flex-direction "row"
                  :flex-wrap "wrap"
                  :justify-content "space-evenly"}}
    (for [stat (sorted-hash-seq stats)]
      ^{:key stat}
      [stat-view name stat])]])

(defn resources-view [name resources]
  "Component for viewing and changing resources."
  [view {:style {:border-width 1
                 :margin 1}}
   [subheading-view "Resources"]
   [view {:style {:flex-direction "row"
                  :flex-wrap "wrap"
                  :justify-content "space-evenly"}}
    (for [res (sorted-hash-seq resources)]
      ^{:key res}
      [res-view name res])]])

(defn debilities-view [name debilities]
  "Component for viewing all debilities."
  [view {:style {:border-width 1
                 :margin 1}}
   [subheading-view "Debilities"]
   [view {:style {:flex-direction "row"
                  :justify-content "space-evenly"
                  :flex-wrap "wrap"}}
    (for [deb (sorted-hash-seq debilities)]
      ^{:key deb}
      [debility-view name deb])]])

(defn vows-view [name vows]
  "Component for viewing all vows."
  (let [new-vow? (atom false)]
    (fn [name vows]
      [view {:style {:flex-direction "column"
                     :justify-content "space-evenly"
                     :border-width 1
                     :margin 1}}
       [subheading-view "Vows"]
       (for [vow (sorted-hash-seq vows)]
         ^{:key vow}
         [vow-view name vow])
       (if @new-vow?
         [text-input {:on-submit-editing #(do
                                            (dispatch [:insert-new-pt %
                                                       :location :vows :char-name name])
                                            (swap! new-vow? not))
                      :placeholder "Enter vow name"}]
         [button {:title "New vow"
                  :on-press #(swap! new-vow? not)}])])))

(declare move-link)
(defn result-view [result]
  "Component for viewing a specific result. Recurses through results in options or random event."
  (let [w100 (atom nil)
        cur-option (atom (first (first (:options result))))]
    (fn [result]
      (if (string? result)
        [text {:style {:flex 1
                       :margin 2
                       :text-align "center"
                       :font-size 16}} result]
        [view
         [text {:style {:text-align "center"
                        :font-size 16
                        :margin 2}}
          (:description result)]
         (when (:options result)
           [view
            [picker {:selected-value @cur-option 
                     :on-value-change (fn [val index]
                                        (reset! cur-option val))}
             (for [opt (map first (:options result))]
               ^{:key opt}
               [picker-item {:label opt
                             :value opt}])]
            (when @cur-option
              [result-view (second
                            (first
                             (filter
                              #(= (first %) @cur-option) ;; probably should move this function to db.cljs
                              (:options result))))])]) ;; this is needed for nested results, especially nested random tables in ask the oracle
         (when (:move result)
           [move-link (:move result)])
         (when (:random-event result)
           [button {:title "Roll" :on-press #(reset! w100 (rolls/roll-d100))}
            :enclosing-style {:margin 2}])
         (when @w100
           [result-view (rolls/get-random-result @w100 (:random-event result))])]))))

(defn custom-note-view [asset & {:keys [char-name]
                                 :or {char-name nil}}]
  "Component for viewing custom notes on assets."
  (let [edit-note? (atom false)]
    (fn  [asset & {:keys [char-name]
                   :or {char-name nil}}]
      (when (and char-name (:custom-note asset))
        [view {:style {:flex-direction "row"
                       :align-items "center"
                       :justify-content "space-evenly"}}
         [text {:style {:margin-right 5}}
          (str (get-in asset [:custom-note 0]) ":")]
         
         (if @edit-note?
           [text-input {:style {:flex 1}
                        :placeholder (get-in asset [:custom-note 1])
                        :on-submit-editing #(do
                                              (swap! edit-note? not)
                                              (dispatch [:change-asset-note char-name asset %]))}]
           [button {:title (get-in asset [:custom-note 1]) :on-press #(swap! edit-note? not)}
            :enclosing-style {:flex 1}])]))))

(defn perk-view [asset perk & {:keys [char-name]
                               :or {char-name nil}}]
  "Component for viewing single perks on assets."
  [view {:style {:flex-direction "row"}}
   (if char-name
     [switch-comp {:value (:enabled perk)
                   :on-value-change #(dispatch-sync [:toggle-perk char-name asset perk])}]
     [switch-comp {:value (:enabled perk)}])
   [view {:style {:flex 1}}
    [result-view (:result perk)]]])

(defn res-counter-view [asset & {:keys [char-name]
                                 :or {char-name nil}}]
  "Component for viewing resource-counters on assets."
  (when (:res-counter asset)
    [view {:style {:flex-direction "row"
                   :justify-content "space-evenly"
                   :padding 2}}
     (when char-name
       [button {:title "-" :on-press #(dispatch [:mod-asset-resource char-name asset -1])}
        :enclosing-style {:width 48 :height 48}])
     [text {:style {:margin 5}} (get-in asset [:res-counter :current])]
     (when char-name
       [button {:title "+" :on-press #(dispatch [:mod-asset-resource char-name asset 1])}
        :enclosing-style {:width 48 :height 48}])]))

(defn asset-view [asset & {:keys [char-name]
                           :or {char-name nil}}]
  "Component for viewing an asset."
  (let [show-asset? (atom false)]
    (fn [asset & {:keys [char-name]
                  :or {char-name nil}}]
      [view {:style {:border-width 1
                     :margin 1}}
       [view {:style {:flex-direction "row"}}
        [button {:title (:name asset)
                 :on-press #(swap! show-asset? not)}
         :enclosing-style {:flex 1}]
        (if char-name
          [button {:title "Remove" :on-press #(dispatch [:rm-ass char-name (:name asset)])}]
          [button {:title "Add" :on-press #(do
                                             (dispatch [:add-ass-to-act-char (:name asset)])
                                             (toast (str "Added: " (:name asset))))}])]
       (when @show-asset?
         [view {:style {:padding 2}}
          [text (:asset-type asset)]
          [custom-note-view asset :char-name char-name]
          (when (:description asset)
            [text (:description asset)])
          (for [perk (:perks asset)]
            ^{:key perk}
            [perk-view asset perk :char-name char-name])
          [res-counter-view asset :char-name char-name]])])))

(defn assets-view [char-name assets]
  "Component for viewing all assets in list"
  [view {:style {:border-width 1
                 :padding 2
                 :margin 1}}
   [subheading-view "Assets"]
   (for [asset assets]
     ^{:key (:name asset)}
     [asset-view asset :char-name char-name])])

(defn xp-view [char]
  "Component for viewing and modifying xp."
  [view
   [text "XP"]
   [view {:style {:flex-direction "row"
                  :align-items "center"
                  :justify-content "space-evenly"
                  :margin 5}}
    [button {:title "-"
             :on-press #(dispatch [:mod-xp (:name char) -1])}
     :enclosing-style {:width 48 :height 48}]
    [text {:style {:padding 5}}
     (:xp char)]
    [button {:title "+"
             :on-press #(dispatch [:mod-xp (:name char) 1])}
     :enclosing-style {:width 48 :height 48}]]])

(defn char-view [name & {:keys [init-show-char?]
                         :or {init-show-char? true}}]
  "Component for viewing and editing a char identified by name."
  (let [char (subscribe [:get-char name])
        show-char? (atom init-show-char?)
        really-delete? (atom false)]
    (fn [name]
      [view
       [view {:style {:flex-direction "row"
                      :justify-content "space-evenly"}}
        [button {:title name 
                 :on-press #(swap! show-char? not)}
         :enclosing-style {:flex 1}]
        (when @show-char?
          (if @really-delete?
            [button {:title "Really Delete"
                     :on-press #(dispatch [:delete-char name])}]
            [button {:title "Delete"
                     :on-press #(swap! really-delete? not)}]))]
       (when @show-char?
         [view
          [view {:style {:flex-direction "row" :justify-content "space-evenly"}}
           [view
            [ini-view name (:initiative @char)]
            [xp-view @char]]
           [momentum-view name (:momentum @char)]]
          [stats-view name (:stats @char)]
          [resources-view name (:resources @char)]
          [debilities-view name (:debilities @char)]
          [vows-view name (:vows @char)]
          [bonds-view name (:bonds @char)]
          [assets-view name (:assets @char)]])])))

(defn chars-view []
  "Component for viewing all chars in db."
  (let [chars (subscribe [:get-chars])
        input-new-char? (atom false)]
    (fn []
      [view {:style {:flex 7}}
       [heading-view "Characters"]
       [scroll-view 
        (for [char-name (keys @chars)]
          ^{:key char-name}
          [char-view char-name :init-show-char? false])
        (if @input-new-char?
          [text-input {:on-submit-editing #(do
                                             (dispatch [:insert-new-char %])
                                             (swap! input-new-char? not))
                       :placeholder "Character Name"}]
          [button {:title "New Character" :on-press #(swap! input-new-char? not)}])]])))



(defn progress-tracks-view []
  "Component for viewing all progress-tracks."
  (let [pts (subscribe [:get-progress-tracks])
        input-new-pt? (atom false)]
    (fn []
      [view {:style {:flex 7}}
       [heading-view "Progress Tracks"]
       (for [pt (sorted-hash-seq @pts)]
         ^{:key pt}
         [progress-view pt])
       (if @input-new-pt?
         [text-input {:on-submit-editing #(do
                                            (dispatch [:insert-new-pt %])
                                            (swap! input-new-pt? not))
                      :placeholder "Insert Name"}]
         [button {:title "New" :on-press #(swap! input-new-pt? not)}])])))

(defn move-link [move]
  "Component for viewing the link to a particular move."
  [view
   [button {:title (:name move) :on-press #(do
                                             (dispatch [:set-active-move move])
                                             (dispatch [:set-screen :move]))}]])



(defn challenge-dice-view [result-atom]
  "Component for viewing and rerolling challenge-dice. Takes an atom (!) as an argument, to enable rerolling." ;; TODO: rerolling, matches!
  [view {:style {:align-items "center"
                 :border-width 1
                 :padding 5}}
   [text "Challenge Dice:"]
   (let [challenges (rolls/get-challenge-ratings @result-atom)]
     [view {:style {:flex-direction "row"
                    :align-items "center"}}
      [view {:style {:align-items "center"}}
       [image {:style {:width 64 
                       :height 64 
                       :margin 1}
               :source (get d10-pics (first challenges))} ]
       [button {:title "Reroll"
                :on-press #(swap! result-atom
                                  rolls/reroll-challenge-die :challenge1)}]]
      [view {:style {:align-items "center"}}
       [image {:style {:width 64
                       :height 64
                       :margin 1}
               :source (get d10-pics (second challenges))} ]
       [button {:title "Reroll"
                :on-press #(swap! result-atom
                                  rolls/reroll-challenge-die :challenge2)}]]
      (when (= (first challenges) (second challenges))
        [text {:style {:margin 5}} "It's a match!"])])])

(defn action-die-view [result-atom]
  "Component for viewing and rerolling the action die. Takes an atom(!) as an arg, to enable rerolling."
  (let [active-char (subscribe [:get-active-char])]
    (fn [result-atom]
      [view {:style {:align-items "center"
                     :border-width 1
                     :padding 1}}
       [text "Action Die"]
       [image {:style {:width 64
                       :height 64
                       :margin 1}
               :source (get d6-pics
                            (rolls/get-action-rating @result-atom))}]
       [button {:title "Reroll"
                :on-press #(swap! result-atom
                                  rolls/reroll-action-die @active-char)}]])))

(defn pick-active-char-view []
  "Component for picking the active character, should use standard picker comp."
  (let [active-char (subscribe [:get-active-char])
        chars (subscribe [:get-chars])]
    [picker {:selected-value (:name @active-char) 
             :on-value-change (fn [val index]
                                (dispatch [:set-active-char val]))}
     (for [char-name (map #(:name (second %)) @chars)]
       ^{:key char-name}
       [picker-item {:label char-name :value char-name}])]))

;; normal-move-view is one of the complex components as of yet and should be broken up a little
(defn normal-move-view []
  "Component for viewing the active move and rolling on it."
  (let [move (subscribe [:get-active-move])
        roll-result (atom nil)
        use-val (atom 0)
        add-val (atom 0)
        active-char (subscribe [:get-active-char])
        chars (subscribe [:get-chars])]
    (fn []
      [scroll-view {:style {:flex 7}}
       [text {:style {:text-align "center"}}
        (:description @move)]
       [view
        [text {:style {:text-align "center"}}
         "Who's rolling?"]
        [pick-active-char-view]]
       (when @active-char
         [view
          [char-view (:name @active-char) :init-show-char? false]
          [view {:style {:flex-direction "row"
                         :flex-wrap "wrap"
                         :justify-content "space-evenly"}}
           [view {:style {:flex-direction "row"
                          :align-items "center"
                          :margin 5}}
            [button {:title "-" :on-press #(swap! use-val dec)}
             :enclosing-style {:width 48 :height 48}]
            [text {:style {:margin 5}} "Use:"]
            [text {:style {:margin 5}} @use-val]
            [button {:title "+" :on-press #(swap! use-val inc)}
             :enclosing-style {:width 48 :height 48}]]
           [view {:style {:flex-direction "row"
                          :align-items "center"
                          :margin 5}}
            [button {:title "-" :on-press #(swap! add-val dec)}
             :enclosing-style {:width 48 :height 48}]
            [text {:style {:margin 5}} "Add"]
            [text {:style {:margin 5}} @add-val]
            [button {:title "+" :on-press #(swap! add-val inc)}
             :enclosing-style {:width 48 :height 48}]]]
          (when @roll-result
            [view {:style {:flex-direction "row"
                           :flex-wrap "wrap"
                           :justify-content "space-evenly"}}
             [action-die-view roll-result] 
             [challenge-dice-view roll-result]]) ;; passing atom here, to allow child view to reroll dice
          [button {:title "Roll" :on-press #(reset! roll-result (rolls/roll-result @active-char))}]
          (when (and @roll-result
                     @active-char
                     (rolls/burn-possible? @roll-result @active-char))
            [button {:title "Burn momentum" :on-press #(do
                                                         (dispatch [:reset-momentum (:name @active-char)])
                                                         (swap! roll-result rolls/burn-momentum @active-char))}])
          (when @roll-result
            (let [result-type (rolls/result-type @roll-result @use-val @add-val)]
              [result-view (get-in @move [:results result-type])]))])])))



(defn progress-roll-view [p-track move]
  "Component for rolling on progress tracks."
  (let [roll-result (atom nil)]
    (fn [p-track move]
      [view
       (when @roll-result
         [challenge-dice-view roll-result]) ;; passing the atom here, for rerolling of dice
       [button {:title "Roll" :on-press #(reset! roll-result (rolls/roll-challenge-dice))}]
       (when @roll-result
         (let [result-type (rolls/result-type-progress @roll-result
                                                       (get p-track 1))]
           [result-view (get-in move [:results result-type])]))])))

(defn progress-move-view []
  "Component for resolving progress moves."
  (let [p-tracks (subscribe [:get-progress-tracks])
        selected-p-track (atom (first (first (sorted-hash-seq @p-tracks)))) ;; why is this not properly reset, if nil is used
        move (subscribe [:get-active-move])]
    (fn []
      [scroll-view {:style {:flex 7}}
       [text {:style {:text-align "center"}}
        (:description @move)]
       [picker {:selected-value @selected-p-track 
                :on-value-change (fn [val index]
                                   (reset! selected-p-track val))}
        (for [pt-name (map first (seq @p-tracks))]
          ^{:key pt-name}
          [picker-item {:label pt-name :value pt-name}])]
       (when @selected-p-track
         [view
          [progress-view (first (filter #(= (first %) @selected-p-track)
                                        (sorted-hash-seq @p-tracks)))]
          [progress-roll-view (get @p-tracks @selected-p-track) @move]])])))

(defn vow-move-view []
  "Component for resolving vow moves."
  (let [chars (subscribe [:get-chars])
        active-char (subscribe [:get-active-char])
        active-vows (subscribe [:get-active-vows])
        selected-vow (atom (first (first (sorted-hash-seq @active-vows))))
        move (subscribe [:get-active-move])
        roll-result (atom nil)]
    (fn []
      [scroll-view {:style {:flex 7}}
       [text {:style {:text-align "center"}}
        (:description @move)]
       [text {:style {:text-align "center"}}
        "Who rolls?"]
       [pick-active-char-view]
       (when @active-char
         [view
          [text {:style {:text-align "center"}}
           "Which vow?"]
          [picker {:selected-value @selected-vow
                   :on-value-change (fn [val index]
                                      (reset! selected-vow val))}
           (for [vow-name (map first (sorted-hash-seq @active-vows))]
             ^{:key vow-name}
             [picker-item {:label vow-name :value vow-name}])] ;;this could be untangled by using full vow as value
          (when @selected-vow
            [view
             [vow-view (:name @active-char)
              [@selected-vow (get @active-vows @selected-vow)]]
             [progress-roll-view (get @active-vows @selected-vow) @move]])])])))

(defn bond-move-view []
  "Component for resolving moves concerning the bonds track."
  (let [chars (subscribe [:get-chars])
        active-char (subscribe [:get-active-char])
        move (subscribe [:get-active-move])]
    (fn []
      [scroll-view {:style {:flex 7}}
       [text (:description @move)]
       [text "Who rolls?"]
       [pick-active-char-view]
       [bonds-view (:name @active-char) (:bonds @active-char)]
       [progress-roll-view ["Epic" (:bonds @active-char)] @move]])))

(defn no-roll-view []
  "Component for resolving moves without rolling the challenge dice."
  (let [move (subscribe [:get-active-move])]
    [scroll-view {:style {:flex 7}}
     [text {:style {:text-align "center"}}
      (:description @move)]
     [result-view (get-in @move [:results "Other"])]]))

(defn moves-list []
  "Component for viewing all moves."
  (let [moves (subscribe [:get-moves])]
    [view {:style {:flex 7}}
     [heading-view "Moves"]
     [scroll-view {:style {:flex 7}}
      (for [move @moves]
        ^{:key (:name move)}
        [move-link move])]]))

(defn asset-list []
  "Component for viewing all assets (and picking one for adding to character)."
  (let [assets (subscribe [:get-all-assets])]
    [view {:style {:flex 7}}
     [heading-view "Assets"]
     [text "Who wants some?"]
     [pick-active-char-view]
     [scroll-view 
      (for [asset @assets]
        ^{:key (:name asset)}
        [asset-view asset])]]))

(defn move-view []
  "Returns component corresponding to :move-type."
  (let [move (subscribe [:get-active-move])]
    [view {:style {:flex 7}}
     [heading-view (:name @move)]
     (case (:move-type @move)
       :normal [normal-move-view]
       :progress-track [progress-move-view]
       :vow-move [vow-move-view]
       :bond-move [bond-move-view]
       :no-roll [no-roll-view])]))

(defn savegame-view [save]
  "Component for rendering all buttons for working on given savefile."
  (let [really-delete? (atom false)]
    (fn [save]
      [view {:margin 1
             :border-width 1}
       (when (= @current-save save)
         [subheading-view "Currently playing:"])
       [view {:style {:flex-direction "row"}}
        [button {:title save
                 :on-press #(do
                              (dispatch [:load-db save])
                              (toast (str "Loading: " save)))}]
        (if @really-delete?
          [button {:title "Really Delete"
                   :on-press #(dispatch [:del-save save])}]
          [button {:title "Delete"
                   :on-press #(swap! really-delete? not)}])
        [text-input {:on-submit-editing #(dispatch [:rename-save save %])
                     :placeholder "New name"}]]])))

(defn savegame-menu []
  "Component for loading and saving games"
  (let [input-new? (atom false)]
    (fn []
      [view {:style {:flex 7}}
       [heading-view "Campaigns"]
       (for [save @all-savegames]
         ^{:key save}
         [savegame-view save])
       (if @input-new?
         [text-input {:on-submit-editing #(do
                                            (swap! input-new? not)
                                            (toast "Please wait a sec.")
                                            (dispatch [:new-game %]))
                      :placeholder "Type a name here"}]
         [button {:title "New Game"
                  :on-press #(swap! input-new? not)}])
       (when (not @current-save)
         [text "Please wait a moment while saved games are loaded, or create a new one and tap its name to play it. Otherwise your progress will be lost."])])))

;; world-views
(defn quest-starter-view [starter]
  "Component for viewing/hiding a starter."
  (let [show-quest-starter? (atom false)]
    (fn [starter]
      [view
       [button {:title "Quest starter"
                :on-press #(swap! show-quest-starter? not)}]
       (when @show-quest-starter?
         [text starter])
       ])))

(defn theme-view [theme]
  "Component for viewing a theme."
  (let [show-quest-starter? (atom false)]
    (fn [theme]
      [view
       [text (first theme)]
       [quest-starter-view (second theme)]])))

(defn topic-view [topic]
  "Component for viewing a topic in world views."
  (let [add-theme? (atom false)
        new-theme (atom {})
        really-delete? (atom false)]
    (fn [topic]
      [view {:style {:border-width 1
                     :margin 2
                     :padding 1}}
       [subheading-view (:name topic)]
       [view
        [picker {:selected-value (:selected-val topic)
                 :on-value-change (fn [val index]
                                    (dispatch [:set-theme topic val]))}
         (for [theme (keys (:themes topic))]
           ^{:key theme}
           [picker-item {:label theme :value theme}])]
        [theme-view (get-in topic [:themes (:selected-val topic)])]
        (when @add-theme?
          [view
           [text-input {:on-change-text (fn [title]
                                             (swap! new-theme #(assoc % :key title)))
                        :placeholder "Title"}]
           [text-input {:on-change-text (fn [description]
                                             (swap! new-theme #(assoc % :description description)))
                        :placeholder "Description"}]
           [text-input {:on-change-text (fn [starter]
                                             (swap! new-theme #(assoc % :starter starter)))
                        :placeholder "Quest starter"}]
           [button {:title "Submit"
                    :on-press #(dispatch [:add-theme topic @new-theme])}]])
        [view {:style {:flex-direction "row"}}
         [button {:title "Add theme"
                  :on-press #(swap! add-theme? not)}
          :enclosing-style {:flex 1}]
         (if @really-delete?
           [button {:title "Really delete theme?"
                    :on-press #(dispatch [:del-theme topic])}
            :enclosing-style {:flex 1}]
           [button {:title "Delete theme"
                    :on-press #(swap! really-delete? not)}
            :enclosing-style {:flex 1}])]]])))

(defn world-view []
  (let [world (subscribe [:get-world])]
    (fn []
      [view {:style {:flex 1}}
       [heading-view "World"]
       [scroll-view {:style {:flex 7}}
        (for [topic @world]
          ^{:key topic}
          [topic-view topic])]])))

(defn location-view [region-name name description]
  "Component for viewing a location."
  (let [edit? (atom false)
        really-delete? (atom false)
        new-name (atom name)
        new-desc (atom description)]
    (fn [region-name name description]
      [view {:style {:margin 2
                     :border-width 1
                     :padding 1}}
       (if @edit?
         [view
          [text-input {:on-change-text #(reset! new-name %)
                       :default-value name}]
          [text-input {:on-change-text #(reset! new-desc %)
                       :default-value description}]
          [button {:title "Submit"
                   :on-press #(do
                                (dispatch [:change-location region-name name @new-name @new-desc])
                                (swap! edit? not))}]]
         [view 
          [subheading-view name]
          [text description]])
       [view {:style {:flex-direction "row"}}
        [button {:title "Edit"
                 :on-press #(swap! edit? not)}
         :enclosing-style {:flex 1}]
        (if @really-delete?
          [button {:title "Really delete?"
                  :on-press #(dispatch [:del-location region-name name])}
           :enclosing-style {:flex 1}]
          [button {:title "Delete"
                   :on-press #(swap! really-delete? not)}
           :enclosing-style {:flex 1}])]])))

(defn region-view [name region]
  "Component for viewing a region."
  (let [show-details? (atom false)]
    (fn [name region]
      [view {:style {:border-width 1
                     :margin 2
                     :padding 1}}
       [button {:title name
                :on-press #(swap! show-details? not)}]
       (when @show-details?
         [view
          [text-list-view "Features" (:features region)]
          [text (:description region)]
          [quest-starter-view (:starter region)]
          (for [loc-name (keys (:locations region))]
            ^{:key loc-name}
            [location-view name loc-name (get-in region [:locations loc-name])])
          [button {:title "Add location"
                   :on-press #(dispatch [:new-location name])}]])])))

(defn regions-view []
  "Component for viewing all regions."
  (let [regions (subscribe [:get-regions])]
    (fn []
      [view {:style {:flex 1}}
       [heading-view "Regions"]
       [scroll-view {:style {:flex 7}}
        (let [derefed-regions @regions]
          (for [region-name (keys derefed-regions)]
            ^{:key region-name}
            [region-view region-name (get derefed-regions region-name)]))]])))

;; foe-views

(defn foe-view [foe]
  "Component for viewing a single foe."
  (let [show-details? (atom false)]
    (fn [foe]
      [view {:style {:margin 1
                     :padding 1
                     :border-width 1}}
       [view {:style {:flex-direction "row"}}
        [button {:title (:name foe)
                 :on-press #(swap! show-details? not)}
         :enclosing-style {:flex 1}]
        [button {:title "Add"
                 :on-press #(do
                              (dispatch [:insert-new-pt (:name foe) :rank (:lvl foe)])
                              (toast (str "Added: " (:name foe))))}]]
       (when @show-details?
         [view
          [text (str "Rank: " (:lvl foe))]
          [text-list-view "Features" (:features foe)]
          [text-list-view "Drives" (:drives foe)]
          [text-list-view "Tactics" (:tactics foe)]
          [text (:description foe)]
          [quest-starter-view (:starter foe)]])])))

(defn foes-view []
  "Component for viewing all foes."
  (let [foes (subscribe [:get-foes])]
    (fn []
      [view {:style {:flex 1}}
       [heading-view "Foes"]
       [scroll-view {:style {:flex 7}}
        (for [foe @foes]
          ^{:key (:name foe)}
          [foe-view foe])]])))

;; views for bond details
(defn bond-detail-view [detail]
  "Component for viewing and editing a single bond detail."
  (let [edit? (atom false)
        really-delete? (atom false)
        new-name (atom (:name detail))
        new-desc (atom (:description detail))]
    (fn [detail]
      (if @edit?
        [view
         [text-input {:on-change-text #(reset! new-name %)
                      :default-value (:name detail)}]
         [text-input {:on-change-text #(reset! new-desc %)
                      :default-value (:description detail)}]
         [button {:title "Submit"
                  :on-press #(do
                               (dispatch [:mod-bond-detail (:name detail) @new-name @new-desc])
                               (swap! edit? not))}]]
        [view
         [subheading-view (:name detail)]
         [text {:style {:text-align "center"
                        :font-size 16
                        :padding 1
                        :margin 1}}
          (:description detail)]
         [view {:style {:flex-direction "row"}}
          [button {:title "Edit"
                   :on-press #(swap! edit? not)}
           :enclosing-style {:flex 1}]
          (if @really-delete?
            [button {:title "Really delete?"
                     :on-press #(do
                                  (dispatch [:del-bond-detail (:name detail)])
                                  (swap! really-delete? not))}
             :enclosing-style {:flex 1}]
            [button {:title "Delete"
                     :on-press #(swap! really-delete? not)}
             :enclosing-style {:flex 1}])]]))))

(defn bond-details-view []
  (let [bond-details (subscribe [:get-bond-details])]
    (fn []
      [view
       [heading-view "Bonds"]
       (for [bond-detail @bond-details]
         ^{:key (:name bond-detail)}
         [view {:style {:border-width 1
                        :margin 1
                        :padding 1}}
          [bond-detail-view bond-detail]])
       [button {:title "New bond"
                :on-press #(dispatch [:add-bond-detail "New bond" "Describe it"])}]])))

;; the licensing and welcome view
(defn about-view []
  "Component for viewing license and generic info."
  [view
   [heading-view "About"]
   [view
    [subheading-view "Ironsworn Companion"]
    [text about-text]
    [text rpg-license-text]
    [text software-license-text]]])

;; Nav-views
(defn nav-menu []
  "Component for picking the active screen."
  [view {:style {:width 180}}
   [heading-view "Ironsworn"]
   (for [screen-name [["Characters" :chars]
                      ["Moves" :move-list]
                      ["Progress Tracks" :progress-tracks]
                      ["Assets" :asset-list]
                      ["Journal" :journal]
                      ["Bonds" :bond-screen]
                      ["World" :world]
                      ["Regions" :region-screen]
                      ["Foes" :foe-screen]
                      ["Campaigns" :savegames]
                      ["About" :about-screen]]]
     ^{:key screen-name}
     [button {:title (first screen-name)
              :on-press #(dispatch [:set-screen (second screen-name)])}])])

(defn main-screen []
  "High-level view that simply invokes the component corresponding to ::active-screen."
  (let [active-screen (subscribe [:get-active-screen])]
    (case @active-screen
      :journal [journal-view]
      :chars [chars-view]
      :world [world-view]
      :progress-tracks [progress-tracks-view]
      :move-list [moves-list]
      :asset-list [asset-list]
      :region-screen [regions-view]
      :savegames [savegame-menu]
      :foe-screen [foes-view]
      :move [move-view]
      :about-screen [about-view]
      :bond-screen [bond-details-view]
      [about-view])))



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

(ns ironsworn-companion.db
  (:require [clojure.spec.alpha :as s]
            [ironsworn-companion.foes :refer [all-foes]]
            [ironsworn-companion.regions :refer [all-regions]]
            [ironsworn-companion.world :refer [world-options]]
            [ironsworn-companion.assets :refer [all-assets]]
            [ironsworn-companion.moves :refer [all-moves]]))

;; default values

(def new-char {:name "New Char"
               :xp 0
               :momentum 2
               :stats {"Edge" 1 "Heart" 1 "Iron" 1 "Shadow" 1 "Wits" 1}
               :vows {}
               :bonds 3 
               :resources {"Health" 5 "Spirit" 5 "Supply" 5}
               :debilities {"Wounded" false
                            "Shaken" false
                            "Unprepared" false
                            "Encumbered" false
                            "Maimed" false
                            "Corrupted" false
                            "Tormented" false
                            "Cursed" false}
               :initiative false
               :assets []})

(def levels ["Troublesome" "Dangerous" "Formidable" "Extreme" "Epic"])

;; spec of app-db

(defn valid-ticks? [ticks]
  "Returns false if ticks are not valid."
  (and (<= ticks 40) (>= ticks 0)))

(def non-empty-string? (s/and string? (comp not empty?)))

(def integer-proba? (s/and integer? #(<= % 100) #(>= % 1)))

(s/def ::name non-empty-string?)
(s/def ::enabled boolean?)

(s/def ::theme (s/tuple ::name ::name))
(s/def ::themes (s/map-of ::name ::theme))
(s/def ::selected-val ::name)
(s/def ::topic (s/keys :req-un [::name ::selected-val ::themes]))
(s/def ::world (s/coll-of ::topic))
(s/def ::journal-entry non-empty-string?)
(s/def ::journal (s/coll-of ::journal-entry))


(s/def ::momentum (s/and integer? #(<= % 10) #(>= % -6)))

(s/def ::stat-name #{"Edge" "Heart" "Iron" "Shadow" "Wits"})
(s/def ::stat (s/and integer? #(<= % 4) #(>= % 1)))
(s/def ::stats (s/map-of ::stat-name ::stat))

(s/def ::progress-track (s/and integer? #(<= % 40) #(>= % 0)))
(s/def ::lvl #(some #{%} levels))
(s/def ::progress (s/tuple ::lvl ::progress-track))

(s/def ::progress-tracks (s/map-of ::name ::progress))

(s/def ::vows (s/map-of ::name ::progress))

(s/def ::bonds (s/and integer? valid-ticks?))

(s/def ::resource (s/and integer? #(<= % 5) #(>= % 0)))
(s/def ::resource-name #{"Health" "Spirit" "Supply"})
(s/def ::resources (s/map-of ::resource-name ::resource))

(s/def ::debility #{"Wounded Unprepared Shaken Encumbered Maimed Corrupted Cursed Tormented"})
(s/def ::debilites (s/map-of ::debility boolean?))

(s/def ::initiative boolean?)

(s/def ::res-counter (s/keys :req-un [::current ::max]))
(s/def ::id (s/or :string ::name
                  :keyword keyword?))
(s/def ::perk (s/keys :req-un [::id ::enabled ::result]))
(s/def ::perks (s/coll-of ::perk))
(s/def ::custom-note (s/tuple ::name ::name))
(s/def ::asset-type #{"Companion" "Ritual" "Path" "Combat Talent"})
(s/def ::asset (s/keys :req-un [::name ::asset-type ::perks]
                       :opt-un [::description ::custom-note ::res-counter]))
(s/def ::assets (s/coll-of ::asset))

(s/def ::xp (s/and integer? #(>= % 0)))
(s/def ::character (s/keys :req-un [::name ::xp ::momentum ::stats ::vows ::bonds ::resources ::debilities ::initiative ::assets]))
(s/def ::characters (s/map-of ::name ::character))

(s/def ::active-char (s/or :empty nil?
                           :char ::name))

(s/def ::d6 (s/and integer? #(<= % 6) #(>= % 1))) ;; these roll sections are very likely obsolete and can be removed
(s/def ::d10 (s/and integer? #(<= % 10) #(>= % 1)))
(s/def ::roll-result (s/or :empty nil?
                           :dice (s/tuple ::d6 ::d10 ::d10)))

(s/def ::oracle ::name)

(s/def ::active-screen #{:chars :roll :move :asset
                         :journal :move-list :asset-list
                         :savegames :progress-tracks :world :region-screen
                         :foe-screen :about-screen :bond-screen})

(s/def ::nav-history (s/coll-of ::active-screen))

(s/def ::description ::name)
(s/def ::result-name #{"Strong Hit" "Weak Hit" "Miss" "Other"})

(s/def ::random-event (s/tuple integer-proba? ::result))
(s/def ::random-events (s/or :empty nil?
                             :list (s/coll-of ::random-event)))
(s/def ::result (s/or :string string?
                      :complex-result (s/keys ::req-un [::description ::options ::random-events]
                                              ::opt-un [::move])))
(s/def ::results (s/map-of ::result-name ::result))
(s/def ::options (s/coll-of (s/tuple ::name ::result)))

(s/def ::move-type #{:normal :progress :progress-vow :progress-bonds})

(s/def ::move (s/keys ::req-un [::move-type ::name ::description ::results]))
(s/def ::moves (s/coll-of ::move))
(s/def ::active-move (s/or :empty nil?
                           :move ::move))

(s/def ::locations (s/map-of ::name ::description))
(s/def ::features (s/coll-of ::description))
(s/def ::starter ::name)
(s/def ::region (s/keys ::req-un [::features ::description ::starter]
                        ::opt-un [::locations]))
(s/def ::regions (s/map-of ::name ::region))

(s/def ::tactics ::features)
(s/def ::drives ::features)
(s/def ::npc-type ::name)
(s/def ::foe (s/keys ::req-un [::name ::lvl ::npc-type ::features ::drives ::tactics ::description ::starter]))
(s/def ::foes (s/coll-of ::foe))

(s/def ::bond-detail (s/keys ::req-un [::name ::description]))
(s/def ::bond-details (s/coll-of ::bond-detail))

(s/def ::version ::name)

(s/def ::app-db
  (s/keys ::req-un [::version
                    ::journal
                    ::characters
                    ::progress-tracks
                    ::active-char
                    ::roll-result
                    ::active-screen
                    ::nav-history
                    ::moves
                    ::world
                    ::regions
                    ::assets
                    ::foes
                    ::active-move
                    ::bond-details]))

;; initial state of app-db
(def app-db {:version "v1"
             :journal (list)
             :characters {}
             :progress-tracks {}
             :active-char nil
             :nav-history (list) 
             :roll-result nil
             :active-screen :savegames
             :moves all-moves
             :assets all-assets
             :regions all-regions
             :world world-options
             :foes all-foes
             :active-move nil
             :bond-details []})

;; model functions
(defn mod-stat [num value]
  "Modifies num by value, assuring it remains in [1,4]."
  (max 1 (min 4 (+ num value))))

(defn mod-res [num value]
  "Modifies num by value, assuring it remains in [0,5]."
  (max 0 (min 5 (+ num value))))

(defn mod-momentum [num value debilities]
  "Modifies num by value, taking into account the number of marked debilities."
  (let [num-debs (count (filter second debilities))]
      (max -6
        (min (- 10 num-debs)
             (+ num value)))))

(defn reset-momentum [num debilities]
  "Resets Momentum based on the number of debilities marked."
  (let [num-debs (count (filter second debilities))]
    (case num-debs
      0 2
      1 1
      0)))

(defn mod-progress [[lvl ticks] value]
  "Updates progress ticks adjusted by value and lvl."
  (let [increment (* value
                     (case lvl
                       "Troublesome" 12
                       "Dangerous" 8
                       "Formidable" 4
                       "Extreme" 2
                       "Epic" 1))
        new-ticks (max 0 (min 40 (+ ticks increment)))]
    (if (valid-ticks? new-ticks)
      [lvl new-ticks]
      [lvl ticks])))

(defn mod-bond-ticks [ticks value]
  "Updated bond ticks adjusted by lvl."
  (let [new-ticks (+ ticks value)]
    (max 0
         (min 40 new-ticks))))

(defn mod-asset-res [asset value]
  "Update res-counter for asset."
  (let [max-val (get-in asset [:res-counter :max])
        new-val (+ (get-in asset [:res-counter :current]) value)]
    (assoc-in asset [:res-counter :current]
              (min max-val
                   (max 0 new-val)))))

;; helper functions for accessing and updating coll elements
(defn update-val-in-coll [coll val new-val]
  "Finds val in coll and replaces it by new-val."
  (map #(if (= val %)
          new-val
          %)
       coll))


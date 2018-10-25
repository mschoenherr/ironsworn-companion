(ns ironsworn-companion.db
  (:require [clojure.spec.alpha :as s]
            [ironsworn-companion.assets :refer [all-assets]]
            [ironsworn-companion.moves :refer [all-moves]]))

;; default values

(def new-char {:name "New Char"
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
               :assets nil})

(def levels #{"Troublesome" "Formidable" "Dangerous" "Extreme" "Epic"})

;; spec of app-db

(defn valid-ticks? [ticks]
  "Returns false if ticks are not valid."
  (and (<= ticks 40) (>= ticks 0)))

(def non-empty-string? (s/and string? (comp not empty?)))

(def integer-proba? (s/and integer? #(<= % 100) #(>= % 1)))

(s/def ::journal-entry non-empty-string?)
(s/def ::journal (s/coll-of ::journal-entry))

(s/def ::name non-empty-string?)

(s/def ::momentum (s/and integer? #(<= % 10) #(>= % -6)))

(s/def ::stat-name #{"Edge" "Heart" "Iron" "Shadow" "Wits"})
(s/def ::stat (s/and integer? #(<= % 4) #(>= % 1)))
(s/def ::stats (s/map-of ::stat-name ::stat))

(s/def ::progress-track (s/and integer? #(<= % 40) #(>= % 0)))
(s/def ::lvl levels)
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
(s/def ::enabled boolean?)
(s/def ::id (s/or ::name keyword?))
(s/def ::perk (s/keys :req-un [::id ::enabled ::result]))
(s/def ::perks (s/coll-of ::perk))
(s/def ::custom-note (s/tuple ::name ::name))
(s/def ::asset-type #{"Companion" "Ritual" "Path" "Combat Talent"})
(s/def ::asset (s/keys :req-un [::name ::asset-type ::perks]
                       :opt-un [::description ::custom-note ::res-counter]))
(s/def ::assets (s/coll-of ::asset))

(s/def ::character (s/keys :req-un [::name ::momentum ::stats ::vows ::bonds ::resources ::debilities ::initiative ::assets]))
(s/def ::characters (s/map-of ::name ::character))

(s/def ::active-char (s/or :empty nil?
                           :char ::name))

(s/def ::d6 (s/and integer? #(<= % 6) #(>= % 1))) ;; these roll sections are very likely obsolete and can be removed
(s/def ::d10 (s/and integer? #(<= % 10) #(>= % 1)))
(s/def ::roll-result (s/or :empty nil?
                           :dice (s/tuple ::d6 ::d10 ::d10)))

(s/def ::oracle ::name)

(s/def ::active-screen #{:chars :roll :move :asset :journal :move-list :asset-list :oracle :progress-tracks})

(s/def ::nav-history (s/coll-of ::active-screen))

(s/def ::description ::name)
(s/def ::result-name #{"Strong Hit" "Weak Hit" "Miss" "Other"})

(s/def ::random-event (s/tuple integer-proba? ::description))
(s/def ::random-events (s/or :empty nil?
                             :list (s/coll-of ::random-event)))
(s/def ::result (s/or :string string?
                      :complex-result (s/keys ::req-un [::description ::options ::random-events])))
(s/def ::results (s/map-of ::result-name ::result))
(s/def ::options (s/or :empty nil?
                       :list (s/coll-of ::result)))

(s/def ::move-type #{:normal :progress :progress-vow :progress-bonds})

(s/def ::move (s/keys ::req-un [::move-type ::name ::description ::results]))
(s/def ::moves (s/coll-of ::move))
(s/def ::active-move (s/or :empty nil?
                           :move ::move))

(s/def ::app-db
  (s/keys :req-un [::journal ::characters ::progress-tracks ::active-char ::roll-result ::oracle ::active-screen ::nav-history ::moves ::active-move]))

;; initial state of app-db
(def app-db {:journal (list)
             :characters {}
             :progress-tracks {}
             :active-char nil
             :nav-history (list)
             :roll-result nil
             :active-screen :chars
             :oracle "Unclear Future"
             :moves all-moves
             :assets all-assets
             :active-move nil})

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

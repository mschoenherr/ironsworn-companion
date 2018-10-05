(ns ironsworn-companion.db
  (:require [clojure.spec.alpha :as s]))

;; spec of app-db
(def non-empty-string? (s/and string? (comp not empty?)))
(s/def ::journal-entry non-empty-string?)
(s/def ::journal (s/coll-of ::journal-entry))

(s/def ::name non-empty-string?)

(s/def ::momentum (s/and integer? #(<= % 10) #(>= % -6)))

(s/def ::stat-name #{"Edge" "Heart" "Iron" "Shadow" "Wits"})
(s/def ::stat (s/and integer? #(<= % 4) #(>= % 1)))
(s/def ::stats (s/map-of ::stat-name ::stat))

(s/def ::progress-track (s/and integer? #(<= % 40) #(>= % 0)))
(s/def ::lvl #{"Troublesome" "Formidable" "Dangerous" "Extreme" "Epic"})
(s/def ::progress (s/tuple ::lvl ::progress-track))

(s/def ::vow (s/tuple ::name ::progress))
(s/def ::vows (s/coll-of ::vow))

(s/def ::bond-names (s/coll-of ::name))
(s/def ::bonds (s/keys :req-un [::bond-names ::progress]))

(s/def ::resource (s/and integer? #(<= % 5) #(>= % 0)))
(s/def ::resource-name #{"Health" "Spirit" "Supply"})
(s/def ::resources (s/map-of ::resource-name ::resource))

(s/def ::debility (s/tuple ::name boolean?))
(s/def ::debilites (s/coll-of ::debility))

(s/def ::initiative boolean?)

(s/def ::character (s/keys :req-un [::name ::momentum ::stats ::vows ::bonds ::resources ::debilities ::initiative]))
(s/def ::characters (s/map-of ::name ::character))

(s/def ::active-char (s/or :empty nil?
                           :char ::character))

(s/def ::d6 (s/and integer? #(<= % 6) #(>= % 1)))
(s/def ::d10 (s/and integer? #(<= % 10) #(>= % 1)))
(s/def ::roll-result (s/or :empty nil?
                           :dice (s/tuple ::d6 ::d10 ::d10)))

(s/def ::oracle ::name)

(s/def ::active-screen #{:chars :roll :move :asset :journal :move-list :asset-list :oracle})

(s/def ::nav-history (s/coll-of ::active-screen))


(s/def ::app-db
  (s/keys :req-un [::journal ::characters ::active-char ::roll-result ::oracle ::active-screen ::nav-history]))

;; initial state of app-db
(def app-db {:journal (list)
             :characters {"Hans" {:name "Hans"
                                  :momentum 2
                                  :stats {"Edge" 2 "Heart" 2}
                                  :vows [["Tear down that wall" ["Dangerous" 0]]]
                                  :bonds {:bond-names ["Georg"] :progress ["Dangerous" 3]}
                                  :resources {"Health" 0}
                                  :debilities [["Wounded" false]]
                                  :initiative false}}
             :active-char nil
             :nav-history (list)
             :roll-result nil
             :active-screen :journal
             :oracle "Unclear Future"})

;; model functions
(defn mod-stat [num value]
  "Modifies num by value, assuring it remains in [1,4]."
  (max 1 (min 4 (+ num value))))

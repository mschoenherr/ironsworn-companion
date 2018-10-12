(ns ironsworn-companion.rolls)

(defn roll-action-die [char]
  "Rolls the action die for the char. Returns tuple of number between 1 and 6
   and a boolean which is false, if the number is equal to -momentum."
  (let [num (inc (rand-int 6))]
    [num
     (not= (- 0 num) (:momentum char))]))

(defn roll-challenge-die []
  "Rolls the challenge die and returns a tuple of the result and true."
  [(inc (rand-int 10)) true])

(defn roll-challenge-dice []
  "Returns two challenge dice."
  [(roll-challenge-die) (roll-challenge-die)])

(defn roll-result [char]
  "Returns a map of roll-results."
  {:action-die (roll-action-die char)
   :challenge1 (roll-challenge-die)
   :challenge2 (roll-challenge-die)})

(defn get-action-rating [result]
  "Returns the rating of the action die or zero if it is disabled."
  (if (get-in result [:action-die 1])
    (get-in result [:action-die 0])
    0))

(defn get-challenge-ratings [result]
  "Returns the ratings of the challenge dice as a vector."
  [(if (get-in result [:challenge1 1])
     (get-in result [:challenge1 0])
     0)
   (if (get-in result [:challenge2 1])
     (get-in result [:challenge2 0])
     0)])

(defn result-type [{:keys [action-die challenge1 challenge2]} use-val add-val]
  "Returns the roll-result, according to enabled or disabled dice."
  (let [action-rating (+ use-val
                         add-val
                         (if (second action-die)
                           (first action-die)
                           0))
        cha1 (if (second challenge1)
               (first challenge1)
               0)
        cha2 (if (second challenge2)
               (first challenge2)
               0)]
    (cond
      (and (> action-rating cha1) (> action-rating cha2)) "Strong Hit"
      (or (> action-rating cha1) (> action-rating cha2)) "Weak Hit"
      :else "Miss")))

(defn burn-momentum [{:keys [action-die challenge1 challenge2] :as result} {:keys [momentum]}]
  "Modifies result to accomodate burn result effect. Char is left unmodified."
  (cond
    (not (and (second challenge1) (second challenge2))) result
    (> momentum (first challenge1)) (update-in result [:challenge1 1] not)
    (> momentum (first challenge2)) (update-in result [:challenge2 1] not)
    :else result))

(defn burn-possible? [result char]
  "Returns true, if momentum can be burned."
  (not= (burn-momentum result char) result))

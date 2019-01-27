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

(defn roll-d100 []
  "Rolls a d100"
  (inc (rand-int 100)))

(defn roll-challenge-dice []
  "Returns two challenge dice."
  {:challenge1 (roll-challenge-die)
   :challenge2 (roll-challenge-die)})

(defn roll-result [char]
  "Returns a map of roll-results."
  {:action-die (roll-action-die char)
   :challenge1 (roll-challenge-die)
   :challenge2 (roll-challenge-die)})

(defn reroll-challenge-die [result key]
  "Rerolls the die given by key and returns changed result."
  (assoc result key (roll-challenge-die)))

(defn reroll-action-die [result char]
  "Rerolls the action die for given char."
  (assoc result :action-die (roll-action-die char)))

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

(defn result-type-progress [{:keys [challenge1 challenge2]} ticks]
  "Returns result for progress rolls."
  (let [cha1 (* 4 (first challenge1))
        cha2 (* 4 (first challenge2))]
    (cond
     (and (> ticks cha1) (> ticks cha2)) "Strong Hit"
     (or (> ticks cha1) (> ticks cha2)) "Weak Hit"
     :else "Miss")))

(defn burn-momentum [{:keys [action-die challenge1 challenge2] :as result} {:keys [momentum]}]
  "Modifies result to accomodate burn result effect. Char is left unmodified."
  (cond
    (not (and (second challenge1) (second challenge2))) result
    (and (> momentum (first challenge1)) (<= (first action-die) (first challenge1))) (update-in result [:challenge1 1] not)
    (and (> momentum (first challenge2)) (<= (first action-die) (first challenge2))) (update-in result [:challenge2 1] not)
    :else result))

(defn burn-possible? [result char]
  "Returns true, if momentum can be burned."
  (not= (burn-momentum result char) result))

(defn get-random-result [w100 random-events]
  "Returns the random-event in [[proba event]] where proba >= w100."
  (second
   (first
    (filter #(>= (first %) w100)
            random-events))))

(ns problem93
  (:use [combinatorics]))

(def operations (permutations-by-n [+ - * /] 3))
(def set-combinations (all-permutations [1 2 3 4]))

(defn combine [[a b c d] 
               [op1 op2 op3]]
  (op3 (op2 (op1 a b) c) d))

(defn valid? [result]
  (and (pos? result) (integer? result)))

(def r 
  (for [nums set-combinations
        ops operations
        :let [result (combine nums ops)]
        :when (valid? result)]
    result))

(sort (distinct r))


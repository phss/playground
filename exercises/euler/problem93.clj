(ns problem93
  (:use [combinatorics]))

(def operations (permutations-by-n [+ - * /] 3))

(defn combine [[a b c d] [op1 op2 op3]]
  (-> (op1 a b)
      (op2 c)  
      (op3 d)))

(defn valid? [result]
  (and (pos? result) (integer? result)))

(defn all-results [s] 
  (for [nums (all-permutations s)
        ops operations
        :let [result (combine nums ops)]
        :when (valid? result)]
    result))

(defn longest-set-n [s]
  (let [results (sort (distinct (all-results s)))]
    (last (last (take-while (fn [[x y]] (= (inc x) y)) (partition 2 1 results))))))

(def x
  (for [all (permutations-by-n [1 2 3 4 5 6 7 8 9] 4)]
    [all (longest-set-n all)]
    )
  )

(sort (distinct (all-results [1 2 5 6])))
;(last (sort-by second x))

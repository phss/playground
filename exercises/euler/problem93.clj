(ns problem93
  (:use [combinatorics]))

(def operations (permutations-by-n [+ - * /] 3))

(def all-sets (->> (permutations-by-n (range 1 10) 4)
                   (map (comp vec sort))
                   (distinct)
                   (filter #(= 4 (count (distinct %))))))

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

;(sort (distinct (all-results [1 2 5 6])))

(defn longest-consecutive-seq [col]
  (->> col
       (map-indexed vector)
       (partition-by(partial apply -))
       (map count)
       (apply max)))

;(longest-consecutive-seq [1 2 3 4 6 8 9 11 12 13 14 15 16])

(defn longest-set-n [s]
  (let [results (sort (distinct (all-results s)))]
    (longest-consecutive-seq results)))


(last (sort-by second (map (fn [s] [s (longest-set-n s)]) all-sets)))


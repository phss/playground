(ns problem93
  (:use [combinatorics]))

(def operations (permutations-by-n [+ - * /] 3))

(def all-sets (->> (permutations-by-n (range 1 10) 4)
                   (map (comp vec sort))
                   (distinct)
                   (filter #(= 4 (count (distinct %))))))

(defn abs [n] (max n (- n)))

(defn combine [[a b c d] [op1 op2 op3]]
  (-> (op1 a b)
      (op2 c)  
      (op3 d)
      (abs))) ; Using absolute solves the precedence problem

(defn all-results [s] 
  (for [nums (all-permutations s)
        ops operations
        :let [result (combine nums ops)]
        :when (integer? result)]
    result))

(defn longest-consecutive-seq [col]
  (->> col
       (map-indexed vector)
       (partition-by(partial apply -))
       (map count)
       (apply max)))

(defn longest-set-n [s]
  (let [results (sort (distinct (all-results s)))]
    (longest-consecutive-seq results)))


(last (sort-by second (map (fn [s] [s (longest-set-n s)]) all-sets)))


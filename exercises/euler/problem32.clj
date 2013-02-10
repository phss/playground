
(defn sqrt [n]
  (int (Math/sqrt n)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn nine-pan? [d]
  (= (range 1 10) (sort d)))

(def pan-prods (for [multiplicand (range 1 99)
                     multiplier (range multiplicand 10000)
                     :let [product (* multiplicand multiplier)]
                     :when (nine-pan? (apply concat (map digits [multiplicand multiplier product])))]
                 [multiplicand multiplier product]))

(println pan-prods)

(println (reduce + (distinct (map last pan-prods))))

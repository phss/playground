
(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (map nth-pent (iterate inc 1)))

(defn pent-op? [op j k]
  (let [r (Math/abs (op (nth-pent j) (nth-pent k)))
        possible-pents (take-while (partial >= r) pents)]
    (= r (last possible-pents))))

(defn valid-pents-upto [n] 
  (for [j (range 1 (dec n))
        k (range (inc j) n)
        :when (pent-op? - j k)]
    (map nth-pent [j k])))

(def valids (valid-pents-upto 2000))

(println (count valids))

;(time (println (take 5 (sort-by (fn [[j k]] (- k j)) valids))))

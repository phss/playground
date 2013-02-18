
(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (map nth-pent (iterate inc 1)))

(defn pent-op? [op j k]
  (let [r (Math/abs (op (nth-pent j) (nth-pent k)))
        possible-pents (take-while (partial >= r) pents)]
    (= r (last possible-pents))))

(println (take 10 pents))

(println (pent-op? + 4 7))
(println (pent-op? - 4 7))


(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (map nth-pent (iterate inc 1)))

(println (take 10 pents))

(use 'commons)

(def factors-seq (map (fn [n] [n (distinct (prime-factors n))]) (iterate inc 1)))

(println (take 20 factors-seq))

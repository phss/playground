(use 'commons)
(use 'clojure.math.combinatorics)

(def all-pans-digits (filter #(not= 0 (first %)) (permutations (range 10))))

(time (println (count all-pans-digits)))

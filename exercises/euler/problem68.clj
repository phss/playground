(use 'commons)
(use 'clojure.math.combinatorics)

(def numbers (range 1 7))

(def ring-numbers (combinations numbers 3))

(println (count ring-numbers))

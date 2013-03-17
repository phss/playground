(use 'commons)
(use 'clojure.math.combinatorics)

(def numbers (range 1 7))

(def ring-numbers (->>
                    (combinations numbers 3)
                    (mapcat (comp reverse permutations))
                    ))

(println (take 10 ring-numbers))

(use 'commons)
(use 'clojure.math.combinatorics)

(def numbers (range 1 7))

(def ring-numbers (->>
                    (combinations numbers 3)
                    (mapcat (comp reverse permutations))))

(defn gon-ring [ring]
  ring)

(println (first (map gon-ring ring-numbers)))

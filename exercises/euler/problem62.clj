(use 'commons)

(defn cube [n]
  (* n n n))

(def cubes (map cube (iterate inc 1)))

(println (take 10 cubes))

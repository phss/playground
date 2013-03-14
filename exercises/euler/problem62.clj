(use 'commons)

(defn cube [n]
  (* n n n))

(defn to-key [c]
  (number-from (sort (digits-from c))))

(def cubes (map cube (iterate inc 1)))

;(def small-5-perm 
;  (loop [perms {} n 1]))

(println (take 10 cubes))
(println (map to-key (take 10 cubes)))

(use 'commons)

(defn cube [n]
  (* n n n))

(defn to-key [c]
  (apply str (sort (digits-from c))))

(def cubes (map cube (iterate inc 1)))

(def small-5-perm 
  (loop [perms {} n 1]
    (let [c (cube n)
          k (to-key c)
          new-perms (update-in perms [k] conj c)
          cube-perms (new-perms k)]
      (if (= 3 (count cube-perms))
        (sort cube-perms)
        (recur new-perms (inc n))))))

(println small-5-perm)

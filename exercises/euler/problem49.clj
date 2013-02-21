(use 'commons)

(def primes (drop-while #(<= % 999) (take-while #(<= % 9999) all-primes)))

(def primes-by-perm (->> primes
                         (group-by #(sort (digits-from %)))
                         (map second)
                         (filter #(<= 3 (count %)))))

(defn arith-seq? [numbers]
  (let [diffs (map (fn [[a b]] (- b a)) (partition 2 1 numbers))]
    (apply = diffs)))

;(println (filter arith-seq? primes-by-perm))

(println primes-by-perm)

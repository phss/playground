(use 'commons)
(use 'clojure.math.combinatorics)

(def primes (drop-while #(<= % 999) (take-while #(<= % 9999) all-primes)))

(def primes-by-perm (->> primes
                         (group-by #(sort (digits-from %)))
                         (map second)
                         (filter #(<= 3 (count %)))))

(defn seq-diffs [numbers]
  (map (fn [[a b]] (- b a)) (partition 2 1 (sort numbers))))

(defn arith-seq? [numbers]
  (apply = (seq-diffs numbers)))

(defn contain-arith-seq? [numbers]
  (let [groups-of-3 (combinations numbers 3)]
    (some arith-seq? groups-of-3)))

(println (second (filter contain-arith-seq?  primes-by-perm)))

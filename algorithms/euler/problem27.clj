
(defn solve-quadratic [n a b]
  (+ (* n n) (* a n) b))

(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (not-any? (partial divisible? n) (range 2 (Math/sqrt n)))))

(defn quadratic-primes [a-range b-range]
  (let [all-quad-primes (for [a a-range b b-range n (range)
                              :while (prime? (solve-quadratic n a b))]
                           [[a b] n])]
    (last (sort-by second all-quad-primes))))


(println (solve-quadratic 5 1 41))
(println (solve-quadratic 5 -79 1601))

(time (println (quadratic-primes (range 1 5) (range 39 44))))

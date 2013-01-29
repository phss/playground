
(defn solve-quadratic [n a b]
  (+ (* n n) (* a n) b))

(defn prime? [n]
  false)

(defn quadratic-primes [a-range b-range]
  (for [a a-range b b-range n (range)
        :while (prime? (solve-quadratic n a b))]
    [a b n]))


(println (solve-quadratic 5 1 41))
(println (solve-quadratic 5 -79 1601))

(time (println (quadratic-primes (range 1 5) (range 39 44))))

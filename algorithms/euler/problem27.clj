
(defn solve-quadratic [n a b]
  (+ (* n n) (* a n) b))

(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (not-any? (partial divisible? n) (range 2 (Math/sqrt n)))))

(defn quadratic-primes [a-range b-range]
  (let [candidates (for [a a-range b b-range :when (prime? (solve-quadratic 40 a b))] [a b])]
    (count candidates)))


(time (println (quadratic-primes (range -999 1000) (range -999 1000))))

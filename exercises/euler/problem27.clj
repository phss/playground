
(defn solve-quadratic [n a b]
  (+ (* n n) (* a n) b))

(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (and (> n 0)
         (not-any? (partial divisible? n) (range 2 (Math/sqrt n))))))

(defn max-quadratic-n [a b]
  (loop [n 0]
    (if (prime? (solve-quadratic n a b))
      (recur (inc n))
      n)))

(defn quadratic-primes [a-range b-range]
  (let [candidates (for [a a-range b b-range 
                         :when (prime? (solve-quadratic 40 a b))] 
                     [[a b] (max-quadratic-n a b)])]
    (last (sort-by second candidates))))


(time (println (quadratic-primes (range -999 1000) (range -999 1000))))
;
;(println (max-quadratic-n -999 61))
;(println (solve-quadratic 11 -999 61))
;(println (prime? -10807))

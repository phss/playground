(defn primes-below [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))
        prime? (fn [n] (not-any? (partial divisible? n) (range 2 n)))
        primes (filter prime? (iterate inc 2))]
    (take-while (partial > n) primes)))

(time (println (apply + (primes-below 10))))

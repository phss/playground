(defn prime [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))
        prime? (fn [n] (not-any? (partial divisible? n) (range 2 n)))
        primes (filter prime? (iterate inc 2))]
    (take n primes)))

(time (println (last (prime 10001))))

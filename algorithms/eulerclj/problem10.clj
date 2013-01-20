
(defn primes-below [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))
        prime? (fn [n factors] (not-any? (partial divisible? n) factors))]
    (loop [primes [2] x 3]
      (cond (> x n) primes
            (prime? x primes) (recur (conj primes x) (+ x 2))
            :else (recur primes (+ x 2))))))


(time (println (apply + (primes-below 2000000)))) ; 76127

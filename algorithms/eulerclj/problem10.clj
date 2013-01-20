
(defn primes-below2 [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))
        prime? (fn [n factors] (not-any? (partial divisible? n) factors))]
    (loop [primes [2] x 3]
      (cond (> x n) primes
            (prime? x primes) (recur (conj primes x) (+ x 2))
            :else (recur primes (+ x 2))))))


(defn primes-below [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (loop [primes [] numbers (range 2 n)]
      (if (empty? numbers)
        primes
        (recur (conj primes (first numbers))
               (remove #(divisible? % (first numbers)) numbers))))))


(time (println (apply + (primes-below 10000)))) ; 5736396 / 1300

(use 'commons)

;(def primes (primes-up-to 1000000))
(def primes (primes-up-to 1000))
(def boundary (last primes))

(defn largest-prime-sum [start]
  (loop [i start sum 0 prime-sum 0]
    (let [new-sum (+ sum (nth primes i))]
      (cond
        (> new-sum boundary) prime-sum
        (prime? new-sum) (recur (inc i) new-sum new-sum)
        :else (recur (inc 1) new-sum prime-sum)))))

(def prime-sums (for [i (range 0 (/ (count primes) 2))] (largest-prime-sum i)))

(println (last (sort prime-sums)))

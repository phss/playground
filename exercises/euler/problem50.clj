(use 'commons)

(def primes (primes-up-to 1000000))
(def boundary (last primes))

(defn largest-prime-sum [start]
  (loop [i start sum 0 prime-sum 0 prime-nums 0]
    (let [new-sum (+ sum (nth primes i))]
      (cond
        (> new-sum boundary) [prime-nums prime-sum]
        (prime? new-sum) (recur (inc i) new-sum new-sum (- start i))
        :else (recur (inc 1) new-sum prime-sum prime-nums)))))

(def prime-sums (for [i (range 0 (/ (count primes) 100))] (largest-prime-sum i)))

(println (last (sort-by first prime-sums)))

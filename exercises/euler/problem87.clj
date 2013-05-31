(use 'commons)

;(def boundary 50)
(def boundary 50000000)

(defn primes-to
  [f]
  (for [p all-primes :let [fp (f p)] :while (< fp boundary)] fp))

(def squared-primes (primes-to pow2))
(def cubed-primes (primes-to #(pow % 3)))
(def fourth-pow-primes (primes-to #(pow % 4)))

;(println (count squared-primes))
;(println (count cubed-primes))
;(println (count fourth-pow-primes))

(time (println (count (distinct
  (for [s squared-primes
        c cubed-primes
        f fourth-pow-primes
        :let [sum (+ s c f)]
        :when (< sum boundary)]
    sum)))))

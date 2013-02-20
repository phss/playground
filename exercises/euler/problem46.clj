(use 'commons)

(def primes (primes-up-to 1000))

(def twice-squares (map #(int (* 2 (pow2 %))) (iterate inc 1)))


(println (take 10 primes))
(println (take 10 twice-squares))

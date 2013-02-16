(use 'commons)

(def primes (primes-up-to 10000000))

(time (println (last (sort (filter pandigital-num? primes)))))

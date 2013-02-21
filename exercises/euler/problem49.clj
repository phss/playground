(use 'commons)

(def primes (drop-while #(<= % 999) (take-while #(<= % 9999) all-primes)))

(println (count primes))

(println (take 10 primes))

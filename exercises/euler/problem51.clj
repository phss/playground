(use 'commons)
(use 'clojure.test)

(def primes (primes-up-to 1000000))

(is (= 4 (count [1 2 3 4 3])))

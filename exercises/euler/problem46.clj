(use 'commons)

(def primes (take 1000 all-primes))

(def twice-squares (take 1000 (map #(int (* 2 (pow2 %))) (iterate inc 1))))

(defn odd-composite? [n]
  (and (odd? n) (not (prime? n))))

(def odd-comps (sort (distinct (for [p primes ts twice-squares
                                     :let [n (+ p ts)]
                                     :when (odd-composite? n)] 
                                 n))))

(println (count odd-comps))

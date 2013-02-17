(use 'commons)

(defn pan-prime? [n] 
  (and (pandigital-num? n) (prime? n)))

(time (println (last (sort (filter pan-prime? (range 1 10000000))))))

(use 'commons)


(def primes (vec (drop-while #(<= % 100000) (take-while #(<= % 999999) all-primes))))

(println "Generated primes")

;(println (map (fn [[k v]] [k (count v)]) (group-by #(reduce + (digits-from %)) primes)))
(println (sort (map first (group-by #(reduce + (digits-from %)) primes))))

(defn sum [n]
  (reduce + (digits-from n)))

(println (map sum [560003 561113 563333 564443 566663 567773 569993]))

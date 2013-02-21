(use 'commons)

(def primes (drop-while #(<= % 999) (take-while #(<= % 9999) all-primes)))

(def primes-by-perm (remove (fn [[k v]] (= 1 (count v))) 
                            (group-by #(sort (digits-from %)) primes)))

(println (count primes-by-perm))

(println (take 10 primes-by-perm))

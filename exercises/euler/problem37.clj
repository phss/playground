(use 'commons)

(defn truncations [truncate-f n]
  (let [d (digits-from n)]
    (loop [truncs [] r d]
      (if (empty? r)
        truncs
        (recur (conj truncs (number-from r)) (truncate-f 1 r))))))

(def primes (filter #(> % 10) (primes-up-to 10000000)))

(def truncatable-primes (filter #(every? prime? (concat (truncations drop %) 
                                                        (truncations drop-last %))) 
                                primes))

(time (println (reduce + (take 11 truncatable-primes))))

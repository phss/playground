(use 'commons)

(defn all-truncations [n]
  (let [d (digits-from n)]
    (loop [truncs [] r d]
      (if (empty? r)
        truncs
        (recur (conj truncs (number-from r)) (drop 1 r))))))

(def primes (filter #(> % 10) (primes-up-to 10000000)))

(println (all-truncations 3797))

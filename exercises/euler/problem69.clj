(use 'commons)

(def max-n 11)

(def factors (apply merge (map (fn [n] {n (prime-factors n)}) (range 1 (inc max-n)))))

(time (println (count factors)))

(defn relative-primes? [a b]
  (not-any? (set (factors a)) (factors b)))


(defn totient [n]
  (count (filter #(relative-primes? n %) (range 1 n))))

(doseq [n (range 2 max-n)]
  (println n (totient n)))

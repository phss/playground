(use 'commons)

(defn totient [n]
  (let [f (distinct (prime-factors n))]
    (int (reduce * (conj (map #(- 1 (/ 1 %)) f) n)))))

(def tots (for [n (range 2 10000)
                :let [t (totient n)
                      f (prime-factors n)]]
            [n t (/ n t) f]))

(doseq [t (sort-by #(nth % 2) tots)]
  (println t))

(println (take-while (partial > 1000000) (reductions * all-primes)))

(use 'commons)

(def max-n 1001)

(def factors (apply merge (map (fn [n] {n (prime-factors n)}) (range 1 (inc max-n)))))

(time (println (count factors)))

(defn relative-primes? [a b]
  (not-any? (set (factors a)) (factors b)))

(def rels (for [n (remove prime? (range 2 (inc max-n)))
                i (range 1 n)
                :when (relative-primes? n i)]
            [n i]))

(def totients (frequencies (map first rels)))

(time (println (->>
           totients
           (map (fn [[n t]] [n (/ n t)]))
           (sort-by second)
           (last))))

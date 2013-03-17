(use 'commons)

(defn relative-primes? [a b]
  (let [factors-a (prime-factors a)
        factors-b (prime-factors b)]
    (not-any? (set factors-a) factors-b)))

(def rels (for [n (range 2 11)
                i (range 1 n)
                :when (relative-primes? n i)]
            [n i]))

(def totients (frequencies (map first rels)))

(println (->>
           totients
           (map (fn [[n t]] [n (/ n t)]))
           (sort-by second)
           (last)))

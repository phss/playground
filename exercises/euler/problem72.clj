(use 'commons)

(def rpf (for [d (range 2 100)
               n (range 1 d)
               :when (= 1 (gcd n d))]
            [n d (double (/ n d))]))

;(time (println (sort-by last rpf)))

(println (sort-by first (frequencies (map second rpf))))

;(doseq [n (sort-by last rpf)]
;  (println n))

(defn count-rpf-to [n]
  (loop [c 0 d 2]
    (cond
      (= n d) c
      (prime? d) (recur (+ c (dec d)) (inc d))
      :else (let [rpfs (filter #(= 1 (gcd % d)) (range 1 d))]
              (recur (+ c (count rpfs)) (inc d))))))

(time (println (count-rpf-to 100)))
(println (count (primes-up-to 100)))

;(time (println (count (map first-factor (range 2 1000001)))))
;(time (println (last (primes-up-to 1000001))))

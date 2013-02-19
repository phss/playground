(use 'commons)

(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (take 2222 (map nth-pent (iterate inc 1))))

(defn pent? [n]
  (zero? (mod (inc (sqrt (inc (* 24 n)))) 6)))

(def sum-diff-pents
  (for [j pents
        k pents
        :let [jk (+ j k)
              jkk (+ k jk)]
        :when (and (not= j k) (pent? jk) (pent? jkk))]
    [j k jk jkk]))

(time (println sum-diff-pents))

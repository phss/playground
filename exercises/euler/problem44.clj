(use 'commons)

(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (take 1000 (map nth-pent (iterate inc 1))))

(defn pent? [n]
  (no-decimal? (sqrt (inc (* 24 n)))))

(def sum-diff-pents
  (for [j pents
        k pents
        :let [jk (+ j k)
              jkk (+ k jk)]
        :when (and (not= j k) (pent? jk) (pent? jkk))]
    [j k]))

(time (println (count sum-diff-pents)))

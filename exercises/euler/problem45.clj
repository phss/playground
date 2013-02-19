(use 'commons)

(defn tri? [n]
  (no-decimal? (sqrt (inc (* 8 n)))))

(defn pent? [n]
  (zero? (mod (inc (sqrt (inc (* 24 n)))) 6)))

(defn hex? [n]
  (no-decimal? (/ (inc (sqrt (inc (* 8 n)))) 4)))

(defn all? [n]
  (and (tri? n) (pent? n) (hex? n)))

(println (tri? 40755))
(println (pent? 40755))
(println (hex? 40755))
(println (all? 40755))

(time (println (first (filter all? (iterate inc 40756)))))

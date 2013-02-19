(use 'commons)

(defn pent? [n]
  (zero? (mod (inc (sqrt (inc (* 24 n)))) 6)))


(println (pent? 40755))

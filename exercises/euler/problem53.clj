(use 'commons)

(def limit 1000000)

(defn combs [n r]
  (/ (factorial n) (* (factorial r) (factorial (- n r)))))

(println (> (factorial 100) limit))

(println (combs 5 3))
(println (combs 23 10))

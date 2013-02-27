(use 'commons)

(def limit 1000000)

(defn combs [n r]
  (/ (factorial n) (* (factorial r) (factorial (- n r)))))

(println (> (factorial 100) limit))

(println (combs 5 3))
(println (combs 23 10))

(println (count (for [n (range 1 101)
                                r (range 0 (inc n))
                                :when (> (combs n r) limit)]
                            [1])))

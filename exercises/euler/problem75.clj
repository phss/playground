(use 'commons)

(defn pyth? [a b c]
  (= (pow2 c) (+ (pow2 a) (pow2 b))))

(defn brute-force-count-bend-ways [l]
  (count (for [a (range 1 l)
               b (range (inc a) l)
               :let [c (- l (+ a b))]
               :when (and (< a b c)
                          (pyth? a b c))]
           [a b c])))

;(println (brute-force-count-bend-ways 12))
;(println (brute-force-count-bend-ways 120))

(def one-way-bend (filter #(= 1 (brute-force-count-bend-ways %)) (iterate inc 1)))


(println (take 100 one-way-bend))

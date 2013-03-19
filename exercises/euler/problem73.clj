(use 'commons)

(def bottom (/ 1 3))
(def top (/ 1 2))

(def rpf (for [d (range 2 9)
               n (range 1 d)
               :let [r (/ n d)]
               :while (< r top)
               :when (and (= 1 (gcd d n))
                          (< bottom r))]
           [d n]))

(time (println (count rpf)))

(use 'commons)

(def perimeters (for [a (range 1 1000)
                      b (range (inc a) 1001)
                      :let [c (pyth a b)]
                      :when (no-decimal? c)]
                  (+ a b (int c))))

(def solutions (frequencies (filter (partial >= 1000) perimeters)))

(println (last (sort-by second solutions)))

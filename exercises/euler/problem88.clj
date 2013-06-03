(use 'commons)

(def natural-numbers (range 1 30))

(println
  (distinct (for [a natural-numbers
        b natural-numbers
        c natural-numbers
        d natural-numbers
        :let [nums [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 a b c d]]
        :when (= (reduce + nums) (reduce * nums))]
    (sort nums))))

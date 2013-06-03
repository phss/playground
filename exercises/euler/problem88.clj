(use 'commons)

(def natural-numbers (range 1 10))

(println
  (distinct (for [a natural-numbers
        b natural-numbers
        c natural-numbers
        d natural-numbers
        e natural-numbers
        f natural-numbers
        g natural-numbers
        h natural-numbers
        :let [nums [a b c d e f g h]]
        :when (= (reduce + nums) (reduce * nums))]
    (sort nums))))

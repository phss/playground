(println (count (distinct (for [a (range 2 101) b (range 2 101)] (Math/pow a b)))))

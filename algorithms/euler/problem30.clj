
(defn pow [n e]
  (int (Math/pow n e)))

(println (for [a (range 10)
               b (range 10)]
           [[a b] (+ (pow a 2) (pow b 2))]))

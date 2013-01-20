(defn square [n] (* n n))

(defn pyth-triplet [sum]
  (for [a (range 1 sum) b (range (inc a) sum) c (range (inc b) sum)
        :when (and (= sum (+ a b c))
                   (= (square c) (+ (square a) (square b))))]
     [a b c]))

(time (println (pyth-triplet 1000)))

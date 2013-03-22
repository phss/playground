(use 'commons)

(def limit 3000000)
;(def limit 400)

(defn pyth? [a b c]
  (= (pow2 c) (+ (pow2 a) (pow2 b))))

(defn brute-force-count-bend-ways [l]
  (count (for [a (range 1 l)
               b (range (inc a) l)
               :let [c (- l (+ a b))]
               :when (and (< a b c)
                          (pyth? a b c))]
           (sort [a b c]))))

(def one-way-bend (filter #(= 1 (brute-force-count-bend-ways %)) (iterate (partial + 2) 2)))

(time (println (take 50 one-way-bend)))

(defn pyth-triple [m n]
  (let [a (- (pow2 m) (pow2 n))
        b (* 2 m n)
        c (+ (pow2 m) (pow2 n))]
    [a b c]))

(def prime-triplet-lengths (for [m (iterate inc 2)
                           n (range 1 m)
                          :when (and (= 1 (gcd m n)) (odd? (- m n)))
                          :let [tri (pyth-triple m n)]]
  (reduce + tri)))

(def triplet-lengths (for [ptl (take-while #(<= % limit) prime-triplet-lengths)
                           c (iterate inc 1)
                           :let [l (* ptl c)]
                           :while (<= l limit)]
                       l))

(time (println (->>
                 triplet-lengths
                 (frequencies)
                 (filter (fn [[len freq]]
                           (and (<= len 1500000)
                                (= 1 freq))))
                 (sort)
                 (count))))

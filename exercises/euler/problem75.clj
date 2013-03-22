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

(def one-way-bend (filter #(= 1 (brute-force-count-bend-ways %)) (iterate (partial + 2) 2)))


(time (println (take 50 one-way-bend)))

(defn pyth-triple [m n]
  (let [a (- (pow2 m) (pow2 n))
        b (* 2 m n)
        c (+ (pow2 m) (pow2 n))]
    (if (and (> a 0) (> b 0) (> c 0)) 
      [a b c]
      nil)))

(println (pyth-triple 5 3))

(def triples (for [m (range 1 1000)
                   n (range 1 1000)]
               (pyth-triple m n)))

(println (take 20 (sort-by first (remove #(nil? (second %)) (map (fn [n] [(reduce + n) n]) triples)))))

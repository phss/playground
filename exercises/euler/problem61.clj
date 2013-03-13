(use 'commons)
(use 'clojure.math.combinatorics)

(defn take-in-range [f]
  (drop-while #(> 1000 %) (take-while #(>= 9999 %) (map f (iterate inc 1)))))

(defn tri [n]
  (int (* n (+ 1 n) 0.5)))

(def tris (take-in-range tri))

(defn square [n]
  (* n n))

(def sqs (take-in-range square))

(defn pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (take-in-range pent))

(defn hex [n]
  (* n (- (* 2 n) 1)))

(def hexs (take-in-range hex))

(defn hept [n]
  (int (* n (- (* 5 n) 3) 0.5)))

(def hepts (take-in-range hept))

(defn oct [n]
  (* n (- (* 3 n) 2)))

(def octs (take-in-range oct))

(defn linked? [a b]
  (let [a-link (take-last 2 (digits-from a))
        b-link (take 2 (digits-from b))]
    (= a-link b-link)))

(defn any-link? [p1 p2 p3 p4 p5 p6]
  (some (fn [[a b c d e f]] 
          (and (linked? a b) (linked? b c) (linked? c d)
               (linked? d e) (linked? e f) (linked? f a)))
        (permutations [p1 p2 p3 p4 p5 p6])))

(def cyclics (for [t tris
                   s sqs
                   p pents
                   hx hexs
                   hp hepts
                   o octs
                   :when (any-link? t s p hx hp o)]
               [t s p hx hp o]))

(time (println (first cyclics)))

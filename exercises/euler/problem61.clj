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

(defn any-link? [a b c]
  (or (and (linked? a b) (linked? b c) (linked? c a))
      (and (linked? a c) (linked? c b) (linked? b a))
      (and (linked? b a) (linked? a c) (linked? c b))
      (and (linked? b c) (linked? c a) (linked? a b))
      (and (linked? c a) (linked? a b) (linked? b c))
      (and (linked? c b) (linked? b a) (linked? a c))))

(def cyclics (for [t tris
                   s sqs
                   p pents
                   :when (any-link? t s p)]
               [t s p]))

(time (println cyclics))

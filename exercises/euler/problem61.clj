(use 'commons)

(defn take-in-range [f]
  (take-while #(>= 9999 %) (map f (iterate inc 1))))

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

(println (count tris))
(println (take 5 tris))
(println (count sqs))
(println (take 5 sqs))
(println (count pents))
(println (take 5 pents))
(println (count hexs))
(println (take 5 hexs))
(println (count hepts))
(println (take 5 hepts))
(println (count octs))
(println (take 5 octs))

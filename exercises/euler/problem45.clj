(use 'commons)

(defn tri? [n]
  (no-decimal? (sqrt (inc (* 8 n)))))

(defn pent? [n]
  (zero? (mod (inc (sqrt (inc (* 24 n)))) 6)))

(defn hex? [n]
  (no-decimal? (/ (inc (sqrt (inc (* 8 n)))) 4)))

(defn all? [n]
  (and (tri? n) (pent? n) (hex? n)))

(defn nth-tri [n]
  (int (* n (inc n) 0.5)))

(defn nth-pent [n]
  (int (* n (dec (* 3 n)) 0.5)))

(defn nth-hex [n]
  (int (* n (dec (* 2 n)))))

(def tris (take 1000 (map nth-tri (iterate inc 1))))
(def pents (take 1000 (map nth-pent (iterate inc 1))))
(def hexes (take 1000 (map nth-hex (iterate inc 1))))

(println (last tris))
(println (last pents))
(println (last hexes))


;(time (println (first (filter all? (iterate inc 40756)))))

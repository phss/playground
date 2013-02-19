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
  (long (* n (inc n) 0.5)))

(defn nth-pent [n]
  (long (* n (dec (* 3 n)) 0.5)))

(defn nth-hex [n]
  (long (* n (dec (* 2 n)))))

(def numbers 90000)
(def tris (take numbers (map nth-tri (iterate inc 1))))
(def pents (take numbers (map nth-pent (iterate inc 1))))
(def hexes (take numbers (map nth-hex (iterate inc 1))))

(time (println (take 3 (filter #(and (some #{%} pents) 
                                     (some #{%} hexes)) 
                               tris))))

;(time (println (first (filter all? (iterate inc 40756)))))

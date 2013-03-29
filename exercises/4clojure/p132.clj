(ns fourclojure.p132)

(defn inbetween [f i coll]
  (if (nil? (rest (rest coll)))
    coll
    (let [a (first coll)
          b (second coll)
          more (rest coll)]
      (concat (if (f a b) [a i] [a]) (lazy-seq (inbetween f i more))))))

(inbetween < :less [2 1])

;(inbetween < :less '(1 6 7 4 3))

;(take 12 (->> [0 1]
;                 (iterate (fn [[a b]] [b (+ a b)]))
;                 (map first) ; fibonacci numbers
;                 (inbetween (fn [a b] ; both even or both odd
;                       (= (mod a 2) (mod b 2)))
;                     :same)))
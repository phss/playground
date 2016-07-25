(ns problem102
  (:use [commons]))

(def positive-tri [-340 495 -153 -910 835 -947])
(def negative-tri [-175  41 -421 -714 574 -645])

; https://en.wikipedia.org/wiki/Barycentric_coordinate_system
(defn barycentric-origin-coords [tri]
  (let [[x1 y1 x2 y2 x3 y3] tri
        x 0
        y 0
        a (- y2 y3)
        b (- x x3)
        c (- x3 x2)
        d (- y y3)
        e (- x1 x3)
        f (- y1 y3)
        g (- y3 y1)
        base (+ (* a e) (* c f))
        b1 (/ (+ (* a b) (* c d)) base)
        b2 (/ (+ (* g b) (* e d)) base)
        b3 (- 1 b1 b2)]
    [b1 b2 b3]))

(defn tri-contain-origin? [tri]
  (every? pos? (barycentric-origin-coords tri)))

;(println (tri-contain-origin? positive-tri))
;(println (tri-contain-origin? negative-tri))

(def triangles (->> 
              "files/problem102.txt"
              (slurp)
              (clojure.string/split-lines)
              (map #(vec (map number-from (clojure.string/split % #","))))
              (vec)))

(println (count (filter tri-contain-origin? triangles)))

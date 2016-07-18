(ns problem102)

(def positive-tri [[-340 495] [-153 -910] [835 -947]])
(def negative-tri [[-175  41] [-421 -714] [574 -645]])

(defn barycentric-origin-coords [tri]
  (let [[x1 x2 x3] (map first tri)
        [y1 y2 y3] (map second tri)
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

(println (tri-contain-origin? positive-tri))
(println (tri-contain-origin? negative-tri))

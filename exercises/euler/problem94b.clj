(ns problem94
  (:use [commons]))

; Inspired by http://www.mathblog.dk/project-euler-94-almost-equilateral-triangles/

(def max-perimeter 1000000000)

; Pell's equation: x^2 - 3y^2 = 1
(def x1 2)
(def y1 1)
(def n 3)

(defn next-solution [x y]
  [(+ (* x1 x) (* n y1 y))
   (+ (* x1 y) (* y1 x))])

(next-solution 7 4)

(ns problem94
  (:use [commons]))

; Inspired by http://www.mathblog.dk/project-euler-94-almost-equilateral-triangles/

(def max-perimeter 1000000000)

; Pell's equation: x^2 - 3y^2 = 1
(def x1 2)
(def y1 1)
(def n 3)

(defn next-solution [[x y]]
  [(+ (* x1 x) (* n y1 y))
   (+ (* x1 y) (* y1 x))])

(defn solutions
  ([] (solutions [x1 y1]))
  ([curr] (cons curr (lazy-seq (solutions (next-solution curr))))))

; Solving the problem
(defn solve [op]
  (for [[x y] (solutions)
        :let [side (/ (op (* 2 x) 1) 3)
              area (* (op x 2) y 1/3)
              perimeter (+ side side (op side 1))]
        :when (and (integer? side) (integer? area) (pos? area))
        :while (< perimeter max-perimeter)]
    perimeter))

(def plus-one-case (solve +)) 
(def minus-one-case (solve -)) 

(reduce + (concat plus-one-case minus-one-case))

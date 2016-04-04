(ns problem101
  (:require  [clojure.math.numeric-tower :as math]))

(defn polynomial-fun [coefficients]
  (fn [n]
    (->>
      (range (count coefficients))
      (map (partial math/expt n))
      (map * coefficients)
      (reduce +))))

(def cube (polynomial-fun [0 0 0 1]))

(def problem (polynomial-fun (take 11 (cycle [1 -1]))))


;(println
  ;(->>
    ;(range 1 12)
    ;(map problem)))

; From http://mathworld.wolfram.com/LagrangeInterpolatingPolynomial.html

(def xs [1 2 3])
(def ys [1 8 27])

(defn pj-x [xs ys j x]
  (let [ks (remove #{j} (range (count xs)))
        c (map (fn [k] (/ (- x (xs k)) (- (xs j) (xs k)))) ks)]
    (reduce * (conj c (ys j)))))

(defn p-x [xs ys x]
  (reduce + (map #(pj-x xs ys % x) (range (count xs)))))

(defn lagrange-fun [xs ys]
  (fn [x] (p-x xs ys x)))

(def lagrange-cube (lagrange-fun xs ys))

(println (lagrange-cube 4))



(ns problem101
  (:require  [clojure.math.numeric-tower :as math]))

(defn polynomial-fun [coefficients]
  (fn [x]
    (->>
      (range (count coefficients))
      (map (partial math/expt x))
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


(defn lagrange-fun [xs ys]
  (fn [x]
    (let [indexes (range (count xs))
          pj-x (fn [j]
            (->>
              indexes
              (remove #{j})
              (map (fn [k] (/ (- x (xs k)) (- (xs j) (xs k))))) 
              (concat [(ys j)])
              (reduce *)))]
      (reduce + (map pj-x indexes)))))

(def lagrange-cube (lagrange-fun xs ys))

(println (lagrange-cube 4))



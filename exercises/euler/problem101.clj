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

(def xs (vec (range 1 5)))
(def ys (vec (map cube xs)))

; From http://mathworld.wolfram.com/LagrangeInterpolatingPolynomial.html
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

(defn bop [xs ys k]
  (let [lfun (lagrange-fun (subvec xs 0 k) (subvec ys 0 k))
        lys (map lfun xs)
        ]
    (->>
      (map vector lys ys)
      (filter #(apply not= %))
      (ffirst))))

(->>
  xs
  (map #(bop xs ys %))
  (filter (complement nil?))
  (reduce +))

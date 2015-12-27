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


(println
  (->>
    (range 1 12)
    (map problem)))


(ns problem100
  (:require  [clojure.math.numeric-tower :as math]))

; Solving a diophanite question:
; 2B^2 - 2B -T^2 + T = 0
; With help from https://www.alpertron.com.ar/QUAD.HTM

(defn next-solution [[t b]]
  [(+ (* 4 b) (* 3 t) -3)
   (+ (* 3 b) (* 2 t) -2)])

(def solutions (iterate next-solution [21 15]))

(println
  (->>
    solutions
    (drop-while (fn [[t b]] (< t 1000000000000)))
    (first)))

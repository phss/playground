(ns problem99
  (:use [commons])
  (:require  [clojure.math.numeric-tower :as math]))

(def numbers (->>
  "files/problem99.txt"
  (slurp)
  (re-seq #"\d+")
  (map (fn [n] (Double/parseDouble n)))
  (partition 2)))

(def huge-numbers
  (map
    (fn [[number n]] (* n (Math/log number)))
    numbers))

(println (apply max-key second (map-indexed vector huge-numbers)))

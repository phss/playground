(ns problem99
  (:require  [clojure.math.numeric-tower :as math]))

(def numbers (->>
  "files/problem99.txt"
  (slurp)
  (re-seq #"\d+")
  (partition 2)))

(println (last numbers))

(ns day1.part1
  (:require
   [day1.calc :as calc]
   [parser :as parser]))

(def input
  (parser/input parser/toInt "data/day1.txt"))

(def result
  (->> input
       (partition 3 1)
       (map #(apply + %))
       (calc/solve)))

(println result)
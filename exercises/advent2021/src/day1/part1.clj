(ns day1.part1
  (:require
   [clojure.string :as s]
   [day1.calc :as calc]))

(def input-file
  (str "data/day1.txt"))

(def input
  (->> input-file
       (slurp)
       (s/split-lines)
       (map #(Integer/parseInt %))))

(def result
  (calc/solve input))

(println result)
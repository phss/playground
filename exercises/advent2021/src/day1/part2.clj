(ns day1.part2
  (:require
   [clojure.string :as s]
   [day1.calc as :calc]))

(def input-file
  (str "data/day1.txt"))

(def input
  (->> input-file
       (slurp)
       (s/split-lines)
       (map #(Integer/parseInt %))))

(def result
  (->> input
       (partition 3 1)
       (map #(apply + %))
       (calc/solve)))

(println result)
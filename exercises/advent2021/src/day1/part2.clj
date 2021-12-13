(ns day1.part2
  (:require [clojure.string :as s]))

(def input-file
  (str "data/day1-input.txt"))

(def input
  (->> input-file
       (slurp)
       (s/split-lines)
       (map #(Integer/parseInt %))))

(defn increase?
  [[a b]]
  (> b a))

(def result
  (->> input
       (partition 3 1)
       (map #(apply + %))
       (partition 2 1)
       (filter increase?)
       (count)))

(println result)
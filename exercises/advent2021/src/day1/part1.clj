(ns day1.part1
  (:require [clojure.string :as s]))

(def input-file
  ;; (first *command-line-args*)
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
       (partition 2 1)
       (filter increase?)
       (count)))

(println result)
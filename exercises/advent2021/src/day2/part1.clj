(ns day2.part1
  (:require
   [clojure.string :as s]))

(def input-file
  (str "data/day2.txt"))

(defn parse-line
  [line]
  (let [[move amount] (s/split line #" ")]
    [move (Integer/parseInt amount)]))

(def input
  (->> input-file
       (slurp)
       (s/split-lines)
       (map parse-line)))

(defn step
  [[position depth]
   [move amount]]
  (case move
    "forward" [(+ position amount) depth]
    "down" [position (+ depth amount)]
    "up" [position (- depth amount)]))

(def final-destination
  (reduce step [0 0] input))

(def result
  (apply * final-destination))

(println result)
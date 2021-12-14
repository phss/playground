(ns day2.part1
  (:require
   [clojure.string :as s]
   [parser :as parser]))

(defn parse-line
  [line]
  (let [[move amount] (s/split line #" ")]
    [move (Integer/parseInt amount)]))

(def input
  (parser/input parse-line "data/day2.txt"))

(defn step
  [[position depth]
   [move amount]]
  (case move
    "forward" [(+ position amount) depth]
    "down" [position (+ depth amount)]
    "up" [position (- depth amount)]))

(def result
  (reduce step [0 0] input))

(def result
  (apply * result))

(println result)
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
  [[position depth aim]
   [move amount]]
  (case move
    "forward" [(+ position amount) (+ depth (* aim amount)) aim]
    "down" [position depth (+ aim amount)]
    "up" [position depth (- aim amount)]))

(def result
  (reduce step [0 0 0] input))

(def result
  (* (first result) (second result)))

(println result)
(ns sudoku-solver.solver
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic.fd :as fd])
  (:use [clojure.core.logic]))


(defn- all-values-within [vars low high]
  (everyg #(fd/in % (fd/interval low high)) vars))

(defn- all-puzzle-locations [vars puz]
  (let [puz-pos (remove #(nil? (second %)) (map list (range) (apply concat puz)))]
    (everyg (fn [[i v]] (== (nth vars i) v)) puz-pos)))

(defn- dynamic-lvars-for [puz]
  (let [n (reduce + (map count puz))]
    (repeatedly n lvar)))

(defn- lvar-rows [vars]
  (->> (partition 9 vars)
       (map vec)
       (vec)))

(defn- lvar-columns [rows]
  (apply map vector rows))

(defn- lvar-squares [rows]
  (for [sqr-start-x (range 0 9 3)
        sqr-start-y (range 0 9 3)]
    (for [x (range sqr-start-x (+ sqr-start-x 3))
          y (range sqr-start-y (+ sqr-start-y 3))]
      (get-in rows [x y]))))

(defn- all-distinct [& groups]
  (everyg fd/distinct (apply concat groups)))


(defn all-solutions-for [puzzle]
  (run* [q]
    (let [vars (dynamic-lvars-for puzzle)
          rows (lvar-rows vars)
          columns (lvar-columns rows)
          squares (lvar-squares rows)]
      (all 
        (== q rows)
        (all-values-within vars 1 9)
        (all-puzzle-locations vars puzzle)
        (all-distinct rows columns squares))))) 

(defn solve [puzzle]
  (first (all-solutions-for puzzle)))

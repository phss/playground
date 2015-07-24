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

(defn- lvar-rows [vars dim]
  (partition dim vars))

(defn- lvar-columns [vars dim]
  (let [index-of (fn [c r] (+ (* dim r) c))
        lvar-at #(nth vars %)] 
    (for [c (range dim)]
      (map (comp lvar-at (partial index-of c)) (range dim)))))


(defn- lvar-squares [vars dim]
  (let [sqdim (/ dim 3)
        a (partition sqdim vars)
        b (partition sqdim a)
        c (partition sqdim b)]
    (apply concat (for [i c]
      (partition 9 (apply concat (apply (partial mapcat vector) i)))
      ))
    )
  )


(defn- all-distinct [& groups]
  (everyg fd/distinct (apply concat groups)))

(defn solve [puzzle]
  (run* [q]
    (let [v (dynamic-lvars-for puzzle)
          at #(nth v %)
          dim (count (first puzzle))
          rows (lvar-rows v dim)
          columns (lvar-columns v dim)
          squares (lvar-squares v dim)]
      (all 
        (== q rows)
        (all-values-within v 1 dim)
        (all-puzzle-locations v puzzle)
        (all-distinct rows columns squares))))) 

; Hacky for testing
(defmacro puzzle-hack [& rows]
  (letfn [(nil-for-missing-val [v] (if (not= '_ v) v))
          (row-with-missing-val [row] (vec (map nil-for-missing-val row)))]
    (vec (map row-with-missing-val rows))))

(def initial-puzzle-hack
  (puzzle-hack [1 _ _ 9 2 _ _ _ _]
          [5 2 4 _ 1 _ _ _ _]
          [_ _ _ _ _ _ _ 7 _]
          [_ 5 _ _ _ 8 1 _ 2]
          [_ _ _ _ _ _ _ _ _]
          [4 _ 2 7 _ _ _ 9 _]
          [_ 6 _ _ _ _ _ _ _]
          [_ _ _ _ 3 _ 9 4 5]
          [_ _ _ _ 7 1 _ _ 6]))

(solve initial-puzzle-hack)

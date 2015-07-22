(ns sudoku-solver.solver
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic.fd :as fd])
  (:use [clojure.core.logic]))


(defn all-infd [vars interval]
  (if (seq vars)
    (all
      (fd/in (first vars) interval)
      (all-infd (next vars) interval))
    succeed))

(defn all-puzzle-locations [vars p]
  (let [pos (first p)]
    (if pos
      (all
        (== (nth vars (first pos)) (second pos))
        (all-puzzle-locations vars (rest p)))
      succeed)))

(defn solve [puzzle]
  (run* [q]
    (let [v (repeatedly 4 lvar)]
      (all 
        (== q v)
        (all-infd v (fd/interval 1 4))
        (fd/distinct q)
        (all-puzzle-locations v (remove #(nil? (second %)) (map list (range) (first puzzle)))))))) 

; Hacky for testing
(defmacro puzzle [& rows]1
  (letfn [(nil-for-missing-val [v] (if (not= '_ v) v))
          (row-with-missing-val [row] (vec (map nil-for-missing-val row)))]
    (vec (map row-with-missing-val rows))))

(def initial-puzzle
  (puzzle [1 _ _ 9 2 _ _ _ _]
          [5 2 4 _ 1 _ _ _ _]
          [_ _ _ _ _ _ _ 7 _]
          [_ 5 _ _ _ 8 1 _ 2]
          [_ _ _ _ _ _ _ _ _]
          [4 _ 2 7 _ _ _ 9 _]
          [_ 6 _ _ _ _ _ _ _]
          [_ _ _ _ 3 _ 9 4 5]
          [_ _ _ _ 7 1 _ _ 6]))

(solve (puzzle [1 2 _ 3]))

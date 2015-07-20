(ns sudoku-solver.solver
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic.fd :as fd])
  (:use [clojure.core.logic]))

(defn solve [puzzle]
  (run* [q]
    (fresh [s1 s2 s3 s4 s5 s6 s7 s8 s9]
      (== q [s1 s2 s3 s4 s5 s6 s7 s8 s9])
      (fd/in s1 s2 s3 s4 s5 s6 s7 s8 s9 (fd/interval 1 9))
      (fd/distinct q)
      (== q puzzle)
      ))) 

; Hacky for testing
(defmacro puzzle [& rows]
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

(solve initial-puzzle)

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



(defn- all-distinct [& groups]
  (everyg fd/distinct (apply concat groups)))

(defn solve [puzzle]
  (run* [q]
    (let [v (dynamic-lvars-for puzzle)
          at #(nth v %)
          dim (count (first puzzle))
          rows (lvar-rows v dim)
          columns (lvar-columns v dim)
          squares [
                   [(at 0) (at 1) (at 2) (at 9) (at 10) (at 11) (at 18) (at 19) (at 20)]
                   [(at 3) (at 4) (at 5) (at 12) (at 13) (at 14) (at 21) (at 22) (at 23)]
                   [(at 6) (at 7) (at 8) (at 15) (at 16) (at 17) (at 24) (at 25) (at 26)]
                   [(at 27) (at 28) (at 29) (at 36) (at 37) (at 38) (at 45) (at 46) (at 47)]
                   [(at 30) (at 31) (at 32) (at 39) (at 40) (at 41) (at 48) (at 49) (at 50)]
                   [(at 33) (at 34) (at 35) (at 42) (at 43) (at 44) (at 51) (at 52) (at 53)]
                   [(at 54) (at 55) (at 56) (at 63) (at 64) (at 65) (at 72) (at 73) (at 74)]
                   [(at 57) (at 58) (at 59) (at 66) (at 67) (at 68) (at 75) (at 76) (at 77)]
                   [(at 60) (at 61) (at 62) (at 69) (at 70) (at 71) (at 78) (at 79) (at 80)]
                   
                   ]]
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

;(solve (puzzle [1 2 _ 3]
               ;[2 3 _ 4]
               ;[3 4 _ 1]
               ;[4 _ _ 2]))
; (solve initial-puzzle)
;(lvar-squares (range 16) 4)

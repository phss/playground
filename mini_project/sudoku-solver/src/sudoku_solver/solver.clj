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
        groups-of-3 (nth (iterate (partial partition sqdim) vars) 3)]
    (->> groups-of-3
         (map #(->> (apply (partial mapcat vector) %)
                    (apply concat)
                    (partition 9)))
         (apply concat))))

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


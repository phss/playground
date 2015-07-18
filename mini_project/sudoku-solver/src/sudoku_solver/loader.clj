(ns sudoku-solver.loader
  (:use [clojure.string :only [split-lines]]))

(defn- to-row [row-string]
  (letfn [(to-val [c] (if (not= \* c) (Integer/parseInt (str c))))]
    (map to-val (seq row-string))))

(defn load-from-file [filename]
  (->> (slurp filename)
       (split-lines)
       (map to-row)))


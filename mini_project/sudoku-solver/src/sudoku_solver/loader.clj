(ns sudoku-solver.loader
  (:use [clojure.string :only [split-lines]]))

(def empty-value-char \.)

(defn- to-row [row-string]
  (letfn [(to-val [c] (if (not= empty-value-char c) (Integer/parseInt (str c))))]
    (map to-val (seq row-string))))

(defn load-grid-from-file [filename]
  (->> (slurp filename)
       (split-lines)
       (map to-row)))

(defn load-single-line-from-file [filename]
  (->> (slurp filename)
       (split-lines)
       (first)
       (to-row)
       (partition 9)))

(load-single-line-from-file "resources/sample-single-line.txt")

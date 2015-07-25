(ns sudoku-solver.loader
  (:use [clojure.string :only [split-lines]]))

(def empty-value-char \.)

(defn- to-values [row-string]
  (letfn [(to-val [c] (if (not= empty-value-char c) (Integer/parseInt (str c))))]
    (map to-val (seq row-string))))

(defn- read-all [filename]
  (split-lines (slurp filename)))

(defn load-grid-from-file [filename]
  (->> (read-all filename)
       (map to-values)))

(defn load-single-line-from-file [filename]
  (->> (read-all filename)
       (map #(partition 9 (to-values %)))))

(load-single-line-from-file "resources/sample-single-line.txt")

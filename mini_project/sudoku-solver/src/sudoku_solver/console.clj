(ns sudoku-solver.console
  (:use [clojure.string :only [join]]))

(defn- printable-row [row]
  (->> row
       (replace {nil \_})
       (join)))

(defn print-puzzle [puzzle]
  (doseq [row puzzle]
    (println (printable-row row))))

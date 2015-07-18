(ns sudoku-solver.loader
  (:use [clojure.string :only [split-lines]]))

(defn to-rows [row-string]
  (map #(if (= "*" %) nil (Integer/parseInt %)) (map str (map str (seq row-string)))))

(defn load-from-file [filename]
  (->> filename
       (slurp)
       (split-lines)
       (map to-rows)))

(load-from-file "resources/sample.txt")

(ns sudoku-solver.core
  (:require [sudoku-solver.loader :as loader]
            [sudoku-solver.solver :as solver]
            [sudoku-solver.console :as console]))

(defn -main [filename]
  (doseq [puzzle (loader/load-single-line-from-file filename)]
    (->> (time (solver/solve puzzle))
         (console/print-puzzle))))

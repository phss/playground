(ns sudoku-solver.core
  (:require [sudoku-solver.loader :as loader]
            [sudoku-solver.solver :as solver]
            [sudoku-solver.console :as console]))

(defn -main [filename]
  (->> filename
       (loader/load-from-file)
       (solver/solve)
       (console/print-puzzle)))

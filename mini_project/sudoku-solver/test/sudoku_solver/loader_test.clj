(ns sudoku-solver.loader-test
  (:require [clojure.test :refer :all]
            [sudoku-solver.loader :refer :all]))

(deftest loading
  (is (= [[1 nil nil 9 2 nil nil nil nil]
          [5 2 4 nil 1 nil nil nil nil]
          [nil nil nil nil nil nil nil 7 nil]
          [nil 5 nil nil nil 8 1 nil 2]
          [nil nil nil nil nil nil nil nil nil]
          [4 nil 2 7 nil nil nil 9 nil]
          [nil 6 nil nil nil nil nil nil nil]
          [nil nil nil nil 3 nil 9 4 5]
          [nil nil nil nil 7 1 nil nil 6]] 
         (load-from-file "resources/sample.txt"))))

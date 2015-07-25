(ns sudoku-solver.loader-test
  (:require [clojure.test :refer :all]
            [sudoku-solver.puzzle-helper :refer :all]
            [sudoku-solver.loader :refer :all]))

(deftest loading-grid-format
  (is (= (puzzle [1 _ _ 9 2 _ _ _ _]
                 [5 2 4 _ 1 _ _ _ _]
                 [_ _ _ _ _ _ _ 7 _]
                 [_ 5 _ _ _ 8 1 _ 2]
                 [_ _ _ _ _ _ _ _ _]
                 [4 _ 2 7 _ _ _ 9 _]
                 [_ 6 _ _ _ _ _ _ _]
                 [_ _ _ _ 3 _ 9 4 5]
                 [_ _ _ _ 7 1 _ _ 6]) 
         (load-grid-from-file "resources/sample.txt"))))

(deftest loading-single-line
  (is (= (puzzle [4 _ _ _ _ _ 8 _ 5]
                 [_ 3 _ _ _ _ _ _ _]
                 [_ _ _ 7 _ _ _ _ _]
                 [_ 2 _ _ _ _ _ 6 _]
                 [_ _ _ _ 8 _ 4 _ _]
                 [_ _ _ _ 1 _ _ _ _]
                 [_ _ _ 6 _ 3 _ 7 _]
                 [5 _ _ 2 _ _ _ _ _]
                 [1 _ 4 _ _ _ _ _ _])
         (load-single-line-from-file "resources/sample-single-line.txt"))))

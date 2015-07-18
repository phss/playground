(ns sudoku-solver.loader-test
  (:require [clojure.test :refer :all]
            [sudoku-solver.loader :refer :all]))

(defmacro puzzle [& rows]
  (letfn [(nil-for-missing-val [v] (if (not= '_ v) v))
          (row-with-missing-val [row] (vec (map nil-for-missing-val row)))]
    (vec (map row-with-missing-val rows))))


(deftest loading
  (is (= (puzzle [1 _ _ 9 2 _ _ _ _]
                 [5 2 4 _ 1 _ _ _ _]
                 [_ _ _ _ _ _ _ 7 _]
                 [_ 5 _ _ _ 8 1 _ 2]
                 [_ _ _ _ _ _ _ _ _]
                 [4 _ 2 7 _ _ _ 9 _]
                 [_ 6 _ _ _ _ _ _ _]
                 [_ _ _ _ 3 _ 9 4 5]
                 [_ _ _ _ 7 1 _ _ 6]) 
         (load-from-file "resources/sample.txt"))))


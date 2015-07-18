(ns sudoku-solver.loader-test
  (:require [clojure.test :refer :all]
            [sudoku-solver.loader :refer :all]))

(defmacro puzzle [& rows]
  (let [nr (fn [row] (vec (map #(if (= '_ %) nil %) row)))]
    (vec (map nr rows))))


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

;(puzzle [1 _ 2 3]
        ;[_ _ _ 2])
;(macroexpand-1 '(puzzle [1 _ 2 3]))

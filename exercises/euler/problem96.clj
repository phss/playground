(ns problem96
  (:use [commons]))

(defn puzzle-from-raw [lines]
  (->> lines
       (rest)
       (mapcat digits-from)))

(def puzzles (->> 
  "files/p096_sudoku.txt"
  (slurp)
  (clojure.string/split-lines)
  (partition 10)  
  (map puzzle-from-raw)))

(first puzzles)


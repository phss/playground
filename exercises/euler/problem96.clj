(ns problem96
  (:use [commons]))

(defn puzzle-from-raw [lines]
  (->> lines
       (rest)
       (mapcat digits-from)
       (vec)))

(def puzzles (->> 
  "files/p096_sudoku.txt"
  (slurp)
  (clojure.string/split-lines)
  (partition 10)  
  (map puzzle-from-raw)))

; Solution
(defn value-at [puzzle row column]
  (puzzle (+ column (* row 9))))

(defn values-at [puzzle rows columns]
  (->> (map #(value-at puzzle %1 %2) rows columns)
       (remove zero?)))

(defn row-values [puzzle row]
  (values-at puzzle (repeat row) (range 9)))

(defn column-values [puzzle column]
  (values-at puzzle (range 9) (repeat column)))

(defn indexes [i]
  (let [base (- i (rem i 3))]
    [base (+ base 1) (+ base 2)]))

(defn box-values [puzzle row column]
  (values-at puzzle
             (mapcat (partial repeat 3) (indexes row))
             (flatten (repeat 3 (indexes column)))))

;(row-values (first puzzles) 3)
;(column-values (first puzzles) 4)
;(box-values (first puzzles) 4 4)

;(defn solve [puzzle])

;(solve (first puzzles))

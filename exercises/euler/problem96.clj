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
(defn index-of [row column]
  (+ column (* row 9)))

(defn value-at [puzzle row column]
  (puzzle (index-of row column)))

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

(defn all-values [puzzle row column]
  (->> (concat
         (row-values puzzle row)
         (column-values puzzle column)
         (box-values puzzle row column))
       (distinct)))

(defn update [puzzle row column value]
  (assoc puzzle (index-of row column) value))

(defn possible-values-at [puzzle row column]
  (let [filled-values (all-values puzzle row column)]
    (remove #(some #{%} filled-values) (range 1 10))))

(defn next-puzzles [puzzle [row column]]
  (map #(update puzzle row column %) (possible-values-at puzzle row column)))

(defn positions-to-fill [puzzle]
  (for [row (range 9)
        column (range 9)
        :when (zero? (value-at puzzle row column))]
    [row column]))

(defn solve [puzzle]
  (loop [puzzle-steps [puzzle] positions (positions-to-fill puzzle)]
    (if (empty? positions) puzzle-steps
      (recur 
        (mapcat #(next-puzzles % (first positions)) puzzle-steps)
        (rest positions)))))

(solve (first puzzles))

(ns problem94
  (:use [commons]))

(def max-perimeter 1000000)

(defn almost-perimeter [equal-side diff-side]
  (+ equal-side equal-side diff-side))

(defn almost-area [equal-side diff-side]
  (let [hb (sqrt (- (pow2 equal-side) (pow2 (/ diff-side 2))))]
    (* diff-side hb 1/2)))

(defn integral-area? [equal-side diff-side]
  (no-decimal? (almost-area equal-side diff-side)))

(def perimeters
  (for [equal-side (range 2 (int (/ max-perimeter 3)))
        diff-side [(dec equal-side) (inc equal-side)]
        :when (integral-area? equal-side diff-side)
        :let [perimeter (almost-perimeter equal-side diff-side)]
        :while (< perimeter max-perimeter)]
    [equal-side diff-side perimeter]))

perimeters

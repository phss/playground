(ns problem94
  (:use [commons]))

(defn almost-perimeter [equal-side diff-side]
  (+ equal-side equal-side diff-side))

;(almost-perimeter 5 6)
;(almost-perimeter 5 4)

(defn almost-area [equal-side diff-side]
  (let [hb (sqrt (- (pow2 equal-side) (pow2 (/ diff-side 2))))]
    (* diff-side hb 1/2)))

;(almost-area 5 6)
;(almost-area 5 5)

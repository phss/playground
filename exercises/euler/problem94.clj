(ns problem94
  (:use [commons]))

; ([5 6 16] [17 16 50] [65 66 196] [241 240 722] [901 902 2704] [3361 3360 10082] [12545 12546 37636]
; [46817 46816 140450] [93686 93687 281059] [174725 174726 524176] [302828 302829 908485]) 

(defn almost-perimeter [equal-side diff-side]
  (+ equal-side equal-side diff-side))

(defn next-iso-heronians [curr-heronians]
  (let [[a b c] (take-last 3 curr-heronians)
        next-iso (+ (* 15 c) (* 15 b -1) a)]
    (conj curr-heronians next-iso)))

(def heros-plus-one (last (take 11 (iterate next-iso-heronians [5 65 901]))))

(def heros-minus-one (last (take 11 (iterate next-iso-heronians [16 240 3360]))))

(def p1 (->> heros-plus-one
             (map (fn [n] (almost-perimeter n (inc n))))
             (filter #(< % 1000000000))
             (reduce +)
             ))

(def p2 (->> heros-minus-one
             (map (fn [n] (almost-perimeter (inc n) n)))
             (filter #(< % 1000000000))
             (reduce +)
             ))
(+ p1 p2)

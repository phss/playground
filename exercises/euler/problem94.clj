(ns problem94
  (:use [commons]))

(def max-perimeter 1000000000)

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
             (filter #(< % max-perimeter))
             (reduce +)
             ))

(def p2 (->> heros-minus-one
             (map (fn [n] (almost-perimeter (inc n) n)))
             (filter #(< % max-perimeter))
             (reduce +)
             ))
(+ p1 p2)

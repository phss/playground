(ns eulerclj.problem5)

;2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
;
;What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

(defn evenly-divisible? [num dividers]
  (every? #(zero? (mod num %)) dividers))

;(evenly-divisible? 2520 (range 1 11)); true

(def dividers (range 1 21))
(def prime-multi (* 2 3 5 7 11 13 17))

(first (filter #(evenly-divisible? % dividers) (map (partial * prime-multi) (iterate inc 1))))

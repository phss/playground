(ns problem95
  (:use [commons]))

(defn divisors [n]
  (filter #(divisible? n %) (range 1 (inc (/ n 2)))))

(def all-divisors (map divisors (range 1 1000001)))

(last all-divisors)

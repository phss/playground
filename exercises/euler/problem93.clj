(ns problem93
  (:use [combinatorics]))

(def operations [+ - * /])

(def s (all-permutations [1 2 3 4]))

(permutations-by-n operations 3)

(ns fibsumgap)

(def fibonacci-seq (map first (iterate (fn [[prev curr]] [curr (+ prev curr)]) [0 1])))

(def fibs (take 10 fibonacci-seq))

(def maxnum (reduce + fibs))

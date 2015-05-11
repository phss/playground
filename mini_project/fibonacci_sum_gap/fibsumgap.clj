(ns fibsumgap)

(def fibonacci-seq (map first (iterate (fn [[prev curr]] [curr (+ prev curr)]) [0 1])))

(def fibbys (take 6 fibonacci-seq))

(def max-num (reduce + fibbys))

(def all-sums
  (loop [sums [] fibs fibbys]
    (if (empty? fibs) (distinct sums)
      (let [[fib & fib-rest] fibs
            new-sums (map #(+ fib %) sums)] 
        (recur (concat sums new-sums [fib]) fib-rest)))))

all-sums


(ns findsum)

(def fibonacci-seq (map first (iterate (fn [[prev curr]] [curr (+ prev curr)]) [0 1])))

(defn fibo-sum [numb]
  (loop [fibs [] n numb]
    (if (zero? n) fibs
      (let [lesser-fibs (take-while #(<= % n) fibonacci-seq)
            largest-fib (last lesser-fibs)]
        (recur (conj fibs largest-fib) (- n largest-fib))))))


(fibo-sum 11002123)

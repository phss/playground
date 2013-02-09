(defn digit-count [n]
  (count (str n)))

(defn n-digit-fib [digits]
  (loop [fib_1 1N fib_2 1N n 2]
    (if (= digits (digit-count fib_1))
      n
      (recur (+ fib_1 fib_2) fib_1 (inc n)))))


(time (println (n-digit-fib 1000)))

(defn digit-count [n]
  (count (str n)))

(defn n-digit-fib [digits]
  (loop [fib_1 1 fib_2 1]
    (if (= digits (digit-count fib_1))
      fib_1
      (recur (+ fib_1 fib_2) fib_1))))


(time (println (n-digit-fib 1000)))

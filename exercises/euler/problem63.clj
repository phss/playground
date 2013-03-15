(use 'commons)

(defn pow [n e]
  (.pow (.toBigInteger (bigint n)) e))

(defn digits-count [n]
  (count (digits-from n)))

(defn count-nth-power [n]
  (loop [i 1 c 0]
    (let [p (pow i n)
          dc (digits-count p)]
      (if (< n dc)
        c
        (recur (inc i) (if (= n dc) (inc c) c))))))

(println (reduce + (take 21 (map count-nth-power (iterate inc 1)))))

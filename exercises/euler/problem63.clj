(use 'commons)

(defn pow [n e]
  (int (Math/pow n e)))

(defn digits-count [n]
  (count (digits-from n)))

(defn with-nth-power? [n]
  (loop [i 1]
    (let [p (pow i n)
          dc (digits-count p)]
      (cond
        (= n dc) true
        (< n dc) false
        :else (recur (inc i))))))

(println (with-nth-power? 5))
(println (with-nth-power? 9))

(println (take 10 (filter with-nth-power? (iterate inc 1))))

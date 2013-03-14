(use 'commons)

(defn pow [n e]
  (.pow (.toBigInteger (bigint n)) e))

(defn digits-count [n]
  (count (digits-from n)))

(defn with-nth-power? [n]
  (loop [i 1]
    (let [p (pow i n)
          dc (digits-count p)]
      (println p dc)
      (cond
        (= n dc) p
        (< n dc) false
        :else (recur (inc i))))))

;(println (with-nth-power? 5))
;(println (with-nth-power? 9))
(println (with-nth-power? 22))

;(println (take 22 (filter with-nth-power? (iterate inc 1))))
;(doseq [n (iterate inc 1)]
;  (let [nth-p (with-nth-power? n)] 
;    (println n nth-p)))

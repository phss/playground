
(defn indexes [size]
  (loop [n 2 idx [0]]
    (if (> n size)
      idx
      (recur (+ n 2) (concat idx (map #(+ (last idx) (* n %)) [1 2 3 4]))))))

(defn spiral-prime-percent [size]
  (let [idx (indexes size)
        spiral (iterate inc 1)]
    (println (map (partial nth spiral) idx))
    (apply + (map (partial nth spiral) idx))))


(println (spiral-prime-percent 7))

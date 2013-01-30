
(defn indexes [size]
  (loop [n 2 idx [0]]
    (if (> n size)
      idx
      (recur (+ n 2) (concat idx (map #(+ (last idx) (* n %)) [1 2 3 4]))))))

(defn sum-spiral-diags [size]
  (let [idx (indexes size)
        spiral (iterate inc 1)]
    (apply + (map (partial nth spiral) idx))))

(println (sum-spiral-diags 5))
(time (println (sum-spiral-diags 1001)))

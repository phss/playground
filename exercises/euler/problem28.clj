
(defn indexes [size]
  (loop [n 2 idx [0]]
    (if (> n size)
      idx
      (recur (+ n 2) (concat idx (map #(+ (last idx) (* n %)) [1 2 3 4]))))))

(defn sum-spiral-diags [size]
  (let [idx (indexes size)
        spiral (iterate inc 1)]
    (apply + (map (partial nth spiral) idx))))

(defn better-sum-spiral-diags [size]
  (let [level-sum (fn [n] (+ (* 4 n n) (* -6 n) 6))
        levels (range 3 (inc size) 2)]
    (reduce (fn [t l] (+ t (level-sum l))) 1 levels)))

(println (sum-spiral-diags 5))
(time (println (better-sum-spiral-diags 1001)))
;(time (println (sum-spiral-diags 1001)))

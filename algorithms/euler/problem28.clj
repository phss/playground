
(defn indexes [size]
  (loop [n 2 idx [0]]
    (if (> n size)
      idx
      (recur (+ n 2) (concat idx (map #(+ (last idx) (* n %)) [1 2 3 4]))))))



;(println (indexes 101))
(println (indexes 5))

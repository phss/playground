
(defn indexes [size]
  (loop [n 2 idx [0]]
    (let [l (last idx)]
      (if (> n size)
        idx
        (recur (+ n 2) (concat idx [(+ l n) (+ l (* n 2)) (+ l (* n 3)) (+ l (* n 4))]))))))

(println (indexes 101))

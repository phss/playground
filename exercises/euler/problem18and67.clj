
(defn maximum-path-sum [tri]
  (let [in-order-tri (reverse tri)
        aggregate-max (fn [n l r] (+ n (max l r)))]
    (reduce 
      (fn [prev curr] 
        (map (fn [i n] (aggregate-max n (nth prev i) (nth prev (inc i)))) 
          (range) curr))
        in-order-tri)))


(println (maximum-path-sum [[3], [7, 4], [2, 4, 6], [8, 5, 9, 3]]))

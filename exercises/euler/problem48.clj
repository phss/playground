
(defn pow [n e]
  (loop [i e p n]
    (if (= 1 i)
      p
      (recur (dec i) (* p n)))))


(println (pow 2 2))
(println (pow 3 3))

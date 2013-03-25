(use 'commons)

(defn add-coin [ways]
  (conj ways [1]))

(defn p-div-by [n]
  (loop [ways [[1]]]
    (if (= n (count ways))
      ways
      (recur (add-coin ways)))))

(time (println (p-div-by 7)))

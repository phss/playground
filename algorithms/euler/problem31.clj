(defn update-for-coin [ways coin]
  (reduce
    (fn [w i] (assoc w i (+ (nth w i)
                            (nth w (- i coin)))))
    (vec ways)
    (range coin (count ways))))

(defn ways-to-make [amount]
  (let [ways-for-1p (map (fn [n] 1) (range (inc amount)))] 
    (loop [ways ways-for-1p coins [2 5 10 20 50 100 200]]
      (if (empty? coins)
        (last ways)
        (recur (update-for-coin ways (first coins)) (rest coins))))))

(time (println (ways-to-make 200)))

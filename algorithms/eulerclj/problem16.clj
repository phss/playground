
(defn exp [x n]
  (reduce * (repeat n x)))

(defn power-digit-sum [n]
  (let [powered (exp 2 n)
        digits (map #(Character/digit % 10) (str powered))]
    (apply + digits)))


(time (println (power-digit-sum 1000)))

(use 'commons)

(defn new-ways [way]
  (let [idx (range 0 (count way))
        possible-ways (map #(vec (sort (update-in way [%] inc))) idx)]
    (conj possible-ways (vec (sort (conj way 1))))))

(defn add-coin [ways]
  (set (mapcat new-ways ways)))

(defn p-div-by [pn]
  (loop [ways #{[1]} n 1]
    (println n (count ways) (sort-by first (frequencies (map count ways))))
    (if (divisible? (count ways) pn)
      ways
      (recur (add-coin ways) (inc n)))))

(time (println (filter #(= 1 (count %)) (p-div-by 100))))

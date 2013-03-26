(use 'commons)

(defn new-ways [way]
  (let [idx (range 0 (count way))
        possible-ways (map #(vec (sort (update-in way [%] inc))) idx)]
    (conj possible-ways (vec (sort (conj way 1))))))

(defn add-coin [ways]
  (set (mapcat new-ways ways)))

(defn p-div-by [n]
  (loop [ways #{[1]}]
    (println (count ways))
    (if (divisible? (count ways) 1000000)
      ways
      (recur (add-coin ways)))))

(time (println (filter #(= 1 (count %)) (p-div-by 7))))

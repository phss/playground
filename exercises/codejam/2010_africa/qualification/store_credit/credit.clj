(defn to-int [n] 
  (Integer/parseInt n))

(def n (to-int (read-line)))

(defn items-to-buy [credit items]
  (for [i (range (count items))
        j (range (count items))
        :let [item-i (nth items i)
              item-j (nth items j)]
        :when (and (not= i j)
                   (= credit (+ item-i item-j)))]
    [(inc i) (inc j)]))

(doseq [i (range n)
        :let [credit (to-int (read-line))
              items-size (read-line)
              items (map to-int (clojure.string/split (read-line) #" "))]]
  (println (str "Case #" (inc i) ": " (clojure.string/join " " (first (items-to-buy credit items))))))

(ns queens)

(defn no-diagonal? [board]
  (let [size (count board)]
    (empty? (for [i (range (dec size))
          j (range (inc i) size)
          :when (= (Math/abs (- j i))
                   (Math/abs (- (nth board j) (nth board i))))]
      [i j]))))

(defn valid? [board]
  (and
    (apply distinct? board)
    (no-diagonal? board)))

(valid? [0 0]) ;=> false
(valid? [0 1]) ;=> false
(valid? [1 3 0 2]) ;=> true

(defn solve [n]
  (loop [state (map vector (range n)) i (dec n)]
    (if (zero? i)
      state
      (recur
        (->> state
             (mapcat (fn [board] (map #(conj board %) (range n))))
             (filter valid?)) 
        (dec i)))))


;(solve 8)

(defn solve-recursive [i n]
  (if (= i 1)
    (map vector (range n))
    (for [solutions (solve-recursive (dec i) n)
          next-solutions (map #(conj solutions %) (range n))
          :when (valid? next-solutions)]
      next-solutions)))


(solve-recursive 4 4)

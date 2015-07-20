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



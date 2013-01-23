
(defn paths-at [i grid cols]
  (let [left (if (= 0 (rem i cols)) 0 (nth grid (dec i)))
        up (if (< i cols) 0 (nth grid (- i cols)))]
    (+ left up)))

(defn lattice-paths [n]
  (let [cols (inc n)
        size (int (Math/pow cols 2))]
    (loop [path-grid [1] i 1]
      (let [paths-at-i (paths-at i path-grid cols)] 
        (if (= i size)
          (last path-grid)
          (recur (conj path-grid paths-at-i) (inc i)))))))


(time (println (lattice-paths 20)))


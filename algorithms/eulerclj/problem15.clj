(defn next-paths [[x y] size]
  (let [right-path [(inc x) y]
        down-path [x (inc y)]]
    (remove (fn [[x y]] (or (> x size) (> y size))) [right-path down-path])))

(defn lattice-paths-first [size]
  (loop [paths [[0 0]]]
    (if (= [size size] (first paths))
      (count paths)
      (recur (reduce 
               (fn [ps paths] (concat ps (next-paths paths size))) 
               [] paths)))))

(defn paths-at [n grid size]
  (let [up-idx ]))

(defn lattice-paths [n]
  (let [size (inc n)]
    (loop [path-grid [1] curr 1]
      (let [current-paths (paths-at curr path-grid)] 
        (if (= curr (Math/pow size 2))
          current-paths
          (recur (conj path-grid current-paths) (inc curr)))))))


(time (println (lattice-paths 6)))

;(doseq [x (range 1 8)]
;  (println x (lattice-paths x)))

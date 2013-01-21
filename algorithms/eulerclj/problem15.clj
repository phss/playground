(defn next-paths [[x y] size]
  (let [right-path [(inc x) y]
        down-path [x (inc y)]]
    (remove (fn [[x y]] (or (> x size) (> y size))) [right-path down-path])))

(defn lattice-paths [size]
  (loop [paths [[0 0]]]
    (if (= [size size] (first paths))
      (count paths)
      (recur (reduce 
               (fn [ps paths] (concat ps (next-paths paths size))) 
               [] paths)))))


(time (println (lattice-paths 20)))

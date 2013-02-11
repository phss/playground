(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn to-num [d]
  (Integer/parseInt (apply str d)))

(defn rotations [n]
  (let [d (digits n)]
    (map (fn [i]
           (let [[bef aft] (split-at i d)]
             (to-num (concat aft bef)))) 
         (range (count d)))))

(println (rotations 197))

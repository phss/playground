(ns rogue-clj.world)

(defn make-world [{w :width h :height} default]
  (vec (repeat w (vec (repeat h default)))))
 
(defn at [world x y]
  (get-in world [x y]))

(defn update [world x y value]
  (assoc-in world [x y] value))
 
(defn make-world-from [world-map]
  (let [size  {:width (count (world-map 0)), 
               :height (count world-map)}
        blank-world (make-world size :blank)
        char-to-cell {"W" :wall}]
    (reduce (fn [world [x y value]] (update world x y value))
            blank-world
            (for [x (range (size :width))
                  y (range (size :height))
                  :when (= \W (get-in world-map [y x]))]
              [x y :wall]))))

(ns rogue-clj.world)

(defn make-world [width height default]
  (vec (repeat width (vec (repeat height default)))))
 
(defn cell-at [world x y]
  (get-in world [x y]))

(defn update-cell [world x y value]
  (assoc-in world [x y] value))

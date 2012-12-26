(ns rogue-clj.world)

(defn make-world [width height default]
  (vec (repeat width (vec (repeat height default)))))
 
(defn at [world x y]
  (get-in world [x y]))

(defn update [world x y value]
  (assoc-in world [x y] value))

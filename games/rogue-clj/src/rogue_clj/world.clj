(ns rogue-clj.world)

(defn make-world [width height]
  (vec (repeat width (vec (repeat height "X")))))
 
(defn cell-at [world x y]
  ((world x) y))
 

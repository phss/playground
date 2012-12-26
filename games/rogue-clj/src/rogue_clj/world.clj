(ns rogue-clj.world)

(defn make-world [width height default]
  (vec (repeat width (vec (repeat height default)))))
 
(defn at [world x y]
  (get-in world [x y]))

(defn update [world x y value]
  (assoc-in world [x y] value))

(defn box-world [w h char-types]
  (let [blank-world (make-world w h (char-types :blank))
        walls (for [x (range w)
                    y (range h)
                    :when (or (zero? x) (zero? y) 
                              (= x (dec w)) (= y (dec h)))] 
                [x y])]
    (reduce (fn [world [x y]] (update world x y (char-types :wall))) 
            blank-world walls)
    ))
 

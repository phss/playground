(ns rogue-clj.world)

(defn make-world [{w :width h :height} default]
  (vec (repeat w (vec (repeat h default)))))
 
(defn at [world x y]
  (get-in world [x y]))

(defn update [world x y value]
  (assoc-in world [x y] value))

(defn box-world [{w :width h :height :as size}]
  (let [blank-world (make-world size :blank)
        walls (for [x (range w)
                    y (range h)
                    :when (or (zero? x) (zero? y) 
                              (= x (dec w)) (= y (dec h)))] 
                [x y])]
    (reduce (fn [world [x y]] (update world x y :wall)) 
            blank-world walls)
    ))
 

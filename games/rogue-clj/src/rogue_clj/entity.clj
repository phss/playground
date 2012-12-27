(ns rogue-clj.entity)

(defn make-entity [t pos]
  {:type t, :position pos})

(defn x [entity]
  (get-in entity [:position :x]))

(defn y [entity]
  (get-in entity [:position :y]))

(defn move [entity dir]
  (-> entity
      (update-in [:position :x] + (first dir))
      (update-in [:position :y] + (last dir))))

(defn make-entities-from [world-map]
  (let [char-to-entity {\P :player, \G :goblin}]
    (for [x (range (count (world-map 0)))
          y (range (count world-map))
          :let [c (get-in world-map [y x])]
          :when (some #{c} (keys char-to-entity))]
      (make-entity (char-to-entity c) {:x x, :y y}))))

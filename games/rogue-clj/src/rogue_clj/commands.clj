(ns rogue-clj.commands
  (:require [rogue-clj.entity :as entity]
            [rogue-clj.world :as world]))

(defn move-player [entities dir world]
  (let [player-id [:player 0]
        player (get-in entities player-id)
        moved-player (entity/move player dir)
        new-position (moved-player :position)] 
    (cond
      (world/blocked? world (new-position :x) (new-position :y)) 
        entities
      :else 
        (assoc-in entities player-id moved-player))))

; Can macro this
(defn move-player-left [entities world]
  (move-player entities [-1 0] world))

(defn move-player-up [entities world]
  (move-player entities [0 -1] world))

(defn move-player-right [entities world]
  (move-player entities [1 0] world))

(defn move-player-down [entities world]
  (move-player entities [0 1] world))


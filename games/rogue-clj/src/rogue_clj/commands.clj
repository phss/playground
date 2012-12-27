(ns rogue-clj.commands
  (:require [rogue-clj.entity :as entity]))

(defn move-player [entities dir world]
  (let [player [:player 0]] 
    (update-in entities player entity/move dir)))

(defn move-player-left [entities world]
  (move-player entities [-1 0] world))

(defn move-player-up [entities world]
  (move-player entities [0 -1] world))

(defn move-player-right [entities world]
  (move-player entities [1 0] world))

(defn move-player-down [entities world]
  (move-player entities [0 1] world))


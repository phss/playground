(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as world]
            [rogue-clj.config :as config]
            [rogue-clj.entity :as entity]
            [rogue-clj.commands :as commands]))


(def world (world/make-world-from config/world-map))
(def entities (ref (group-by :type (entity/make-entities-from config/world-map))))

(def triggers {203 commands/move-player-left,
               200 commands/move-player-up,
               205 commands/move-player-right,
               208 commands/move-player-down,
               57 "wait"})

(defn to-absolute-pos [n axis]
  (let [dimension ({:on-x :width, :on-y :height} axis)]
    (* n (config/cell dimension))))
 
(defn draw [graphics cell x y]
  (.drawString graphics (config/cell-representation cell) 
                        (to-absolute-pos x :on-x) 
                        (to-absolute-pos y :on-y)))

(defn game []
  (proxy [BasicGame] ["Rogue in Clojure"]
    (init [container])
    (update [container delta])

    (keyPressed [k c] 
      (if (some #{k} (keys triggers))
        (dosync (alter entities (triggers k) world))))

    (render [container graphics]
      (doseq [x (range (world/width world)) 
              y (range (world/height world))]
        (draw graphics (world/at world x y) x y))
      (doseq [e (flatten (vals @entities))]
        (draw graphics (e :type) (entity/x e) (entity/y e))))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (to-absolute-pos (world/width world) :on-x) 
                     (to-absolute-pos (world/height world) :on-y) 
                     false)
    (.setTargetFrameRate 60)
    (.setShowFPS false)
    (.start)))

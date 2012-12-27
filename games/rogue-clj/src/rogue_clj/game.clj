(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as world]
            [rogue-clj.config :as config]
            [rogue-clj.entity :as entity]))


(def world (ref (world/make-world-from config/world-map)))

(def player (entity/make-entity :player  {:x 1, :y 2}))

(def goblin (entity/make-entity :goblin  {:x 12, :y 2}))

(defn to-absolute-pos [n axis]
  (let [dimension ({:on-x :width, :on-y :height} axis)]
    (* n (config/cell dimension))))
 
(defn update-world [w]
  (let [rx (rand-int (world/width @world))
        ry (rand-int (world/height @world))]
    (world/update w rx ry "X")))

(defn draw [graphics cell x y]
  (.drawString graphics (config/cell-representation cell) 
                        (to-absolute-pos x :on-x) 
                        (to-absolute-pos y :on-y)))

(defn game []
  (proxy [BasicGame] ["Rogue in Clojure"]
    (init [container])
    (update [container delta]
;      (dosync (alter world update-world))
    )
    (render [container graphics]
      (doseq [x (range (world/width @world)) 
              y (range (world/height @world))]
        (draw graphics (world/at @world x y) x y))
      (doseq [ent [player goblin]]
        (draw graphics (ent :type) 
                       (entity/x ent) 
                       (entity/y ent))))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (to-absolute-pos (world/width @world) :on-x) 
                     (to-absolute-pos (world/height @world) :on-y) 
                     false)
    (.setTargetFrameRate 60)
    (.start)))

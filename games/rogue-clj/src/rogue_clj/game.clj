(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]
            [rogue-clj.config :as config]))



(def world (ref (w/box-world config/world-size)))

(def player {:x (/ (config/world-size :width) 2),
             :y (/ (config/world-size :height) 2)})

(def goblin {:x (rand-int (config/world-size :width)),
             :y (rand-int (config/world-size :height))})

(defn update-world [w]
  (let [rx (rand-int (config/world-size :width))
        ry (rand-int (config/world-size :height))]
    (w/update w rx ry "X")))

(defn draw [graphics c x y]
  (.drawString graphics (config/cell-types c) (* x (config/cell :width)) (* y (config/cell :height))))

(defn game []
  (proxy [BasicGame] ["Rogue in Clojure"]
    (init [container])
    (update [container delta]
;      (dosync (alter world update-world))
    )
    (render [container graphics]
      (doseq [x (range (config/world-size :width)) 
              y (range (config/world-size :height))]
        (draw graphics (w/at @world x y) x y))
      (draw graphics :player (player :x) (player :y))
      (draw graphics :goblin (goblin :x) (goblin :y)))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (* (config/world-size :width) (config/cell :width)) 
                     (* (config/world-size :height) (config/cell :height)) 
                     false)
    (.setTargetFrameRate 60)
    (.start)))

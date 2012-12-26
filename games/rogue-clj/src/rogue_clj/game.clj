(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]
            [rogue-clj.config :as config]))

(def cell {:width 12, :height 15})

(def cell-types {:blank ".",
                 :wall "X",
                 :player "@",
                 :goblin "G"})

(def world (ref (w/box-world config/world-size cell-types)))

(def player {:x (/ (config/world-size :width) 2),
             :y (/ (config/world-size :height) 2)})

(def goblin {:x (rand-int (config/world-size :width)),
             :y (rand-int (config/world-size :height))})

(defn update-world [w]
  (let [rx (rand-int (config/world-size :width))
        ry (rand-int (config/world-size :height))]
    (w/update w rx ry "X")))

(defn draw [graphics c x y]
  (.drawString graphics c (* x (cell :width)) (* y (cell :height))))

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
      (draw graphics (cell-types :player) (player :x) (player :y))
      (draw graphics (cell-types :goblin) (goblin :x) (goblin :y)))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (* (config/world-size :width) (cell :width)) 
                     (* (config/world-size :height) (cell :height)) 
                     false)
    (.setTargetFrameRate 60)
    (.start)))

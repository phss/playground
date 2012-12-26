(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]))

(def world-dim {:width 50, :height 30})
(def cell {:width 12, :height 15})

(def cell-types {:blank ".",
                 :wall "X",
                 :player "@",
                 :goblin "G"})

(def world (ref (w/box-world (world-dim :width) (world-dim :height) cell-types)))

(def player {:x (/ (world-dim :width) 2),
             :y (/ (world-dim :height) 2)})

(def goblin {:x (rand-int (world-dim :width)),
             :y (rand-int (world-dim :height))})

(defn update-world [w]
  (let [rx (rand-int (world-dim :width))
        ry (rand-int (world-dim :height))]
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
      (doseq [x (range (world-dim :width)) 
              y (range (world-dim :height))]
        (draw graphics (w/at @world x y) x y))
      (draw graphics (cell-types :player) (player :x) (player :y))
      (draw graphics (cell-types :goblin) (goblin :x) (goblin :y)))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (* (world-dim :width) (cell :width)) 
                     (* (world-dim :height) (cell :height)) 
                     false)
    (.setTargetFrameRate 60)
    (.start)))

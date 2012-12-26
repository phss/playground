(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]))

(def world-dim {:width 50, :height 30})
(def cell {:width 12, :height 15})

(def world (ref (w/make-world (world-dim :width) (world-dim :height) ".")))

(defn update-world [w]
  (let [rx (rand-int (world-dim :width))
        ry (rand-int (world-dim :height))]
    (w/update w rx ry "X")))

(defn game []
  (proxy [BasicGame] ["Rogue in Clojure"]
    (init [container])
    (update [container delta]
      (dosync (alter world update-world))
    )
    (render [container graphics]
      (doseq [x (range (world-dim :width)) 
              y (range (world-dim :height))]
        (.drawString graphics (w/at @world x y) 
                              (* x (cell :width)) 
                              (* y (cell :height)))))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (* (world-dim :width) (cell :width)) 
                     (* (world-dim :height) (cell :height)) 
                     false)
    (.setTargetFrameRate 60)
    (.start)))

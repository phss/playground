(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]))

(def world-dim {:width 100, :height 60})
(def cell {:width 12, :height 15})

(def world (ref (w/make-world (world-dim :width) (world-dim :height) ".")))

(defn update-world [w]
  (let [rx (rand-int (world-dim :width))
        ry (rand-int (world-dim :height))]
    (assoc w rx (assoc (w rx) ry "X"))))

(defn hello-world []
  (proxy [BasicGame] ["Hello World"]
    (init [container])
    (update [container delta]
      (dosync (alter world update-world))
    )
    (render [container graphics]
      (doseq [x (range (world-dim :width)) 
              y (range (world-dim :height))]
        (.drawString graphics (w/cell-at @world x y) 
                              (* x (cell :width)) 
                              (* y (cell :height)))))))

(defn -main [& args]
  (doto (AppGameContainer. (hello-world))
    (.setDisplayMode (* (world-dim :width) (cell :width)) 
                     (* (world-dim :height) (cell :height)) 
                     false)
    (.start)))

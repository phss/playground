(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]))

(def counter (ref 0))
(def world-dim {:width 100, :height 60})
(def cell {:width 12, :height 15})

(def world (ref (w/make-world (world-dim :width) (world-dim :height) "X")))


(defn trans-pos [n size padding]
  (+ padding (* n size)))



(defn hello-world []
  (proxy [BasicGame] ["Hello World"]
    (init [container])
    (update [container delta]
   ;   (dosync (alter counter inc))
    )
    (render [container graphics]
      (doseq [x (range (world-dim :width)) y (range (world-dim :height))]
        (.drawString graphics (w/cell-at @world x y) 
                              (trans-pos x (cell :width) 0) 
                              (trans-pos y (cell :height) 0))))))

(defn -main [& args]
  (doto (AppGameContainer. (hello-world))
    (.setDisplayMode (* (world-dim :width) (cell :width)) 
                     (* (world-dim :height) (cell :height)) 
                     false)
    (.start)))

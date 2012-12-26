(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]))

(def text "This is a counter: ")
(def counter (ref 0))
(def cell {:width 12, :height 15})

(defn trans-pos [n size padding]
  (+ padding (* n size)))

(def world (w/make-world 20 10))


(defn hello-world []
  (proxy [BasicGame] ["Hello World"]
    (init [container])
    (update [container delta]
      (dosync (alter counter inc)))
    (render [container graphics]
      (.drawString graphics (str text @counter) 100 100)
      (doseq [x (range 20) y (range 10)]
        (.drawString graphics (w/cell-at world x y) 
                              (trans-pos x (cell :width) 20) 
                              (trans-pos y (cell :height) 140))))))

(defn -main [& args]
  (doto (AppGameContainer. (hello-world))
    (.setDisplayMode (* 50 (cell :width)) (* 30 (cell :height)) false)
    (.start)))

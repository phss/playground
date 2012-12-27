(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame))
  (:require [rogue-clj.world :as w]
            [rogue-clj.config :as config]))


(def world (ref (w/make-world-from config/world-map)))

(def player {:x (/ (config/world-size :width) 2),
             :y (/ (config/world-size :height) 2)})

(def goblin {:x (rand-int (config/world-size :width)),
             :y (rand-int (config/world-size :height))})

(defn to-absolute-pos [n axis]
  (let [dimension ({:on-x :width, :on-y :height} axis)]
    (* n (config/cell dimension))))
 
(defn update-world [w]
  (let [rx (rand-int (config/world-size :width))
        ry (rand-int (config/world-size :height))]
    (w/update w rx ry "X")))

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
      (doseq [x (range (config/world-size :width)) 
              y (range (config/world-size :height))]
        (draw graphics (w/at @world x y) x y))
      (draw graphics :player (player :x) (player :y))
      (draw graphics :goblin (goblin :x) (goblin :y)))))

(defn -main [& args]
  (doto (AppGameContainer. (game))
    (.setDisplayMode (to-absolute-pos (w/width world) :on-x) 
                     (to-absolute-pos (config/world-size :height) :on-y) 
                     false)
    (.setTargetFrameRate 60)
    (.start)))

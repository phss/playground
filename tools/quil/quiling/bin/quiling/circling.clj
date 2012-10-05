(ns quiling.circling
   (:use quil.core))

(def canvas-size 512)

(defn setup []  
;  (smooth)
;  (no-loop)
;  (background 255)
  )

(defn draw []
;  (if (mouse-pressed)
  (fill 255)
;    (fill 255))
;  (ellipse (mouse-x) (mouse-y) 80 80)

  (println (mouse-x))
  (ellipse 10 10 80 80))

(defsketch circling
  :title "Circling"
  :setup setup
  :draw draw
  :size [canvas-size canvas-size])


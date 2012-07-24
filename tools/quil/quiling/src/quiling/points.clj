(ns quiling.points
  (:use quil.core))

(def canvas-size 512)

(defn- gen-color [x y]
  (let [adjust #(mod % canvas-size)]
    (adjust (* x y))))

(defn setup []  
  (smooth)
  (no-loop)
  (background 255)
  (size canvas-size canvas-size))

(defn draw []
  (doseq [x (range canvas-size)  
          y (range canvas-size)]
    (stroke (gen-color x y))
    (point x y)))

(defsketch points
  :title "Points"
  :setup setup
  :draw draw
  :size [canvas-size canvas-size])
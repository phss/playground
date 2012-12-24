(ns rogue-clj.game
  (:gen-class)
  (:import (org.newdawn.slick AppGameContainer BasicGame)))

(def text "This is a counter: ")
(def counter (ref 0))

(defn hello-world []
  (proxy [BasicGame] ["Hello World"]
    (init [container])
    (update [container delta]
      (dosync (alter counter inc)))
    (render [container graphics]
      (.drawString graphics (str text @counter) 100 100)
      (.drawString graphics (str (.getWidth (.getFont graphics) "A")) 100 115)
      (.drawString graphics "ABiDE" 100 130))))

(defn -main [& args]
  (doto (AppGameContainer. (hello-world))
    (.setDisplayMode 800 600 false)
    (.start)))

(ns slick-clj.core
  (:import (org.newdawn.slick AppGameContainer BasicGame)))

(defn hello-world []
  (proxy [BasicGame] ["Hello World"]
    (init [container] )
    (update [container delta] )
    (render [container graphics] (.drawString graphics "Hello World" 100 100))))

(defn -main [& args]
  (doto (AppGameContainer. (hello-world))
    (.setDisplayMode 800 600 false)
    (.start)))

(ns slick-clj.core
  (:import (org.newdawn.slick AppGameContainer BasicGame GameContainer)))

(defn game []
  (proxy [BasicGame] ["Title?"]
;    (init [container] (println "Init"))
    (update [container delta] (println "Update"))
    (init [container graphics] (println "Render"))))

(defn weird-string [s]
  (proxy [Object] []
    (toString [] s)))

(defn -main
  "I don't do a whole lot."
  [& args]
  (doto (AppGameContainer. (game))
    (.start)))

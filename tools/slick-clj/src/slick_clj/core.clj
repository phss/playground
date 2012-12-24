(ns slick-clj.core
  (:import (org.newdawn.slick BasicGame)))

(defn hello-world []
  (proxy [BasicGame] ["Hello World"]
    (init [container] (println "Init"))
    (update [container delta] (println "Update"))
    (render [container graphics] (println "Render"))))

(defn -main [& args]
  (println "I am main!"))

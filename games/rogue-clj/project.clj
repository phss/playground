(let [native-path (str "-Dorg.lwjgl.librarypath=" (.getAbsolutePath (clojure.java.io/file "native")))] 
  (defproject rogue-clj "1.0.0-SNAPSHOT"
    :description "FIXME: write description"
    :jvm-opts [~native-path]
    :dependencies [[org.clojure/clojure "1.4.0"]
                   [org.clojars.jyaan/slick "247.1"]
                   [org.clojars.jyaan/slick-lwjgl "247.1"]
                   [org.clojars.jyaan/slick-native "247.1"]]
    :main rogue-clj.game))

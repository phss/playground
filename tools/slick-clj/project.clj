(defproject slick-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :main slick-clj.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Djava.library.path=lib"]
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojars.jyaan/slick "247.1"]
                 [org.clojars.jyaan/slick-lwjgl "247.1"]
                 [org.clojars.jyaan/slick-native "247.1"]])

;:native-dependencies [[org.clojars.jyaan/slick-native "247.1"]]

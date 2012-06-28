(ns random_narrative.core
  (:require [random_narrative.haiku :as haiku]
            [random_narrative.sentence :as sentence]
            [clojure.string :as string]))

(defn write [poem]
  (println (string/join "\n" poem)))

(defn -main []
  (let [poem (sentence/generate)]
    (write poem)))

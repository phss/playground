(ns random_narrative.core
  (:require [random_narrative.haiku :as haiku]
            [clojure.string :as string]))

(defn write [poem]
  (println (string/join "\n" poem)))

(defn -main []
  (let [poem (haiku/generate)]
    (write poem)))

(ns parser
  (:require [clojure.string :as s]))

(defn input
  [parseFn file-name]
  (->> file-name
       (slurp)
       (s/split-lines)
       (map parseFn)))

(defn toInt
  [s]
  (Integer/parseInt s))


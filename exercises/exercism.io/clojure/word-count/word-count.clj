(ns phrase
  (:use [clojure.string :only [split]]))

(defn word-count [phrase]
  (let [words (split phrase #" ")]
    (frequencies words)))


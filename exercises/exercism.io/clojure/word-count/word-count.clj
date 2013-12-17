(ns phrase
  (:use [clojure.string :only [lower-case]]))

(defn word-count [phrase]
  (let [words (re-seq #"\w+" (lower-case phrase))]
    (frequencies words)))


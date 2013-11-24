(ns phrase
  (:use [clojure.string :only [lower-case]]))


(defn word-count [phrase]
  (let [any-word-pattern #"\w+"
        words (re-seq any-word-pattern (lower-case phrase))]
    (frequencies words)))


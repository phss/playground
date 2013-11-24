(ns phrase
  (:use [clojure.string :only [split replace lower-case]]))

(defn- sanitize [phrase]
  (let [lower-phrase (lower-case phrase)] 
    lower-phrase))

(defn word-count [phrase]
  (let [words (split (sanitize phrase) #" ")]
    (frequencies words)))


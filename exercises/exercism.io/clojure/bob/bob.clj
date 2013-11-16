(ns bob
  (:use clojure.string))

(defn shouting? [phrase]
  (= phrase (upper-case phrase)))

(defn quiet? [phrase]
  (= (trim phrase) ""))

(defn response-for [phrase]
  (cond
    (quiet? phrase) "Fine. Be that way!"
    (shouting? phrase) "Woah, chill out!"
    :else "Whatever."))

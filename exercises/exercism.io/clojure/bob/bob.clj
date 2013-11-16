(ns bob
  (:use clojure.string))

(defn shouting? [phrase]
  (= phrase (upper-case phrase)))

(defn quiet? [phrase]
  (= (trim phrase) ""))

(defn question? [phrase]
  (= \? (last phrase)))

(defn response-for [phrase]
  (cond
    (quiet? phrase) "Fine. Be that way!"
    (shouting? phrase) "Woah, chill out!"
    (question? phrase) "Sure."
    :else "Whatever."))

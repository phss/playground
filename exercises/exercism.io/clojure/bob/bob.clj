(ns bob
  (:use clojure.string))

(defn shouting? [phrase]
  (and (re-find #"\p{Upper}" phrase)
      (not (re-find #"\p{Lower}" phrase))))

(defn quiet? [phrase]
  (= (trim phrase) ""))

(defn question? [phrase]
  (= \? (last phrase)))

(defn response-for [phrase]
  (cond
    (shouting? phrase) "Woah, chill out!"
    (question? phrase) "Sure."
    (quiet? phrase) "Fine. Be that way!"
    :else "Whatever."))

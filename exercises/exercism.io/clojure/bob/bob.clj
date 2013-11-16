(ns bob)

(defn shouting? [phrase]
  (= phrase (clojure.string/upper-case phrase)))

(defn response-for [phrase]
  (cond
    (shouting? phrase) "Woah, chill out!"
    :else "Whatever."))

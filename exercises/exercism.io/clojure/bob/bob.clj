(ns bob
  (:use [clojure.string :only [trim]]))

(defn response-for [phrase]
  (let [shouting? (and (re-find #"\p{Upper}" phrase) (not (re-find #"\p{Lower}" phrase)))
        question? (= \? (last phrase))
        quiet? (= (trim phrase) "")] 
    (cond
      shouting? "Woah, chill out!"
      question? "Sure."
      quiet? "Fine. Be that way!"
      :else "Whatever.")))

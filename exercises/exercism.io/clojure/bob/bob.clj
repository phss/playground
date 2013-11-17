(ns bob
  (:use [clojure.string :only [trim]]))

(defn response-for [phrase]
  (let [matches (fn [& patterns] (every? #(re-find % phrase) patterns))
        shouting? (matches #"\p{Upper}" #"^[^\p{Lower}]*$")
        question? (= \? (last phrase))
        quiet? (= (trim phrase) "")] 
    (cond
      shouting? "Woah, chill out!"
      question? "Sure."
      quiet? "Fine. Be that way!"
      :else "Whatever.")))

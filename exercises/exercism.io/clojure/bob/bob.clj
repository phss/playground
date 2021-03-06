(ns bob)

(defn response-for [phrase]
  (let [matches (fn [& patterns] (every? #(re-find % phrase) patterns))
        shouting? (matches #"\p{Upper}" #"^[^\p{Lower}]*$")
        question? (matches #"\?$")
        quiet?    (matches #"^\s*$")] 
    (cond
      shouting? "Woah, chill out!"
      question? "Sure."
      quiet?    "Fine. Be that way!"
      :else     "Whatever.")))

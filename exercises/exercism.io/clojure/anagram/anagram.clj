(ns anagram)

(defn anagrams-for [s col]
  (let [anagram? (fn [a] (= (sort s) (sort a)))]
    (filter anagram? col)))

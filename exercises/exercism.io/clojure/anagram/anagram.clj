(ns anagram)

(defn anagrams-for [word candidates]
  (let [keify #(sort %)
        anagram? (fn [a] (= (keify word) (keify a)))]
    (filter anagram? candidates)))

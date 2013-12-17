(ns anagram
  (:use [clojure.string :only [lower-case]]))

(defn anagrams-for [word candidates]
  (let [keify #(sort (lower-case %))
        anagram? (fn [a] (= (keify word) (keify a)))]
    (filter anagram? candidates)))

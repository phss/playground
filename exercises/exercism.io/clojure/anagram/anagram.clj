(ns anagram
  (:use [clojure.string :only [lower-case]]))

(defn anagrams-for [word candidates]
  (let [keify #(sort (lower-case %))
        anagram? (fn [candidate] (and (= (keify word) (keify candidate))
                                      (not= word candidate)))]
    (filter anagram? candidates)))

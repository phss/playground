(ns anagram
  (:use [clojure.string :only [lower-case]]))

(defn anagrams-for [word candidates]
  (letfn [(keify [string] (sort (lower-case string)))
          (anagram? [candidate]
            (and (= (keify word) (keify candidate)) (not= word candidate)))]
    (filter anagram? candidates)))

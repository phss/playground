(ns anagram
  (:use [clojure.string :only [lower-case]]))

(defn anagrams-for [word candidates]
  (let [keify #(sort (lower-case %))
        anagram? #(and (= (keify word) (keify %))
                                      (not= word %))]
    (filter anagram? candidates)))

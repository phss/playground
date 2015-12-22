(ns problem98
  (:require  [clojure.math.combinatorics :as combo])
  (:use [commons]))

(def words (->>
  "files/problem98.txt"
  (slurp)
  (re-seq #"\w+")))

(defn str-identity [s]
  (apply str (sort s)))

(def possible-anagram-pairs
  (->>
    words
    (group-by str-identity)
    (map second)
    (filter #(> (count %) 1))  
    (mapcat #(combo/combinations % 2))))
;(println possible-anagram-pairs)

(def some-squares
  (->>
    (range)
    (map pow2)
    (take-while #(< % 1000000000))))

(def square-anagrams
  (->>
    some-squares
    (map str)
    (group-by str-identity)
    (filter #(> (count (second %)) 1))
    (filter #(unique-digits? (first %)))))

;(println (sort-by #(count (first %)) square-anagrams))
(def possible-square-pairs
  (->>
    square-anagrams
    (map second)
    (mapcat #(combo/combinations % 2))
    ))

(println possible-square-pairs)

(defn pair-mask [pair]
  (let [index-mask (into {} (map vector (first pair) (range)))]
    (->>
      (apply str pair)
      (map index-mask)
      (apply str))))

;(pair-mask ["care" "race"])

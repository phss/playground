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

(defn unique-digits? [n]
  (let [digits (digits-from n)]
    (= digits (distinct digits))))

(def squares (map pow2 (range))) ; Biggest has 9 digits
(def some-squares (take-while #(< % 1000000000) squares))

(def square-anagrams (->>
  some-squares
  (map str)
  (group-by str-identity)
  (filter #(> (count (second %)) 1))
  (filter #(unique-digits? (first %)))))

;(println (sort-by #(count (first %)) square-anagrams))

(defn pair-mask [pair]
  (let [index-mask (into {} (map vector (first pair) (range)))]
    (->>
      (apply str pair)
      (map index-mask)
      (apply str))))

;(pair-mask ["care" "race"])

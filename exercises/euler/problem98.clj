(ns problem98
  (:require  [clojure.math.combinatorics :as combo])
  (:use [commons]))

(def words (->>
  "files/problem98.txt"
  (slurp)
  (re-seq #"\w+")))

(def some-squares
  (->>
    (range)
    (map pow2)
    (take-while #(< % 1000000000))))

(defn str-identity [s]
  (apply str (sort s)))

(defn generate-possible-pairs [col]
  (->>
    col
    (group-by str-identity)
    (map second)
    (filter #(> (count %) 1))  
    (mapcat #(combo/combinations % 2))))

(def possible-anagram-pairs (generate-possible-pairs words))
;(println possible-anagram-pairs)

(def possible-square-pairs (generate-possible-pairs (map str some-squares)))
;(println (count possible-square-pairs))

(defn pair-mask [pair]
  (let [index-mask (into {} (map vector (first pair) (range)))]
    (->>
      (apply str pair)
      (map index-mask)
      (apply str))))

;(pair-mask ["care" "race"])

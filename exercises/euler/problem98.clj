(ns problem98
  (:use [commons]))

(def words (->>
  "files/problem98.txt"
  (slurp)
  (re-seq #"\w+")))

(defn str-identity [s]
  (apply str (sort s)))

;(str-identity "dsad1231")

(def anagrams (->>
  words
  (group-by str-identity)
  (filter #(> (count (second %)) 1))))

(println (map second anagrams))

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


(ns problem98)

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


anagrams

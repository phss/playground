(ns problem98)

(def words (->>
  "files/problem98.txt"
  (slurp)
  (re-seq #"\w+")))

words


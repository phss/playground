
(def num-to-str {
  1 "one",
  2 "two",
  3 "three",
  4 "four",
  5 "five"                
})

(defn num-letter-count [nums]
  (count (mapcat num-to-str nums)))

(println (num-letter-count (range 1 6)))

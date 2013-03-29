(ns fourclojure.p70)

(defn ws [phrase]
  (let [sort-by-word #(sort-by clojure.string/lower-case %)
        split #(clojure.string/split % #"\W+")]
    (sort-by-word (split phrase))))

(ws "Have a nice day.")
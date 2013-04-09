(use 'commons)

(def attempts (->> 
                (slurp "files/problem79.txt")
                (clojure.string/split-lines)
                (map seq)
                (distinct)))

(defn order [[a b c]]
  [[a b] [a c] [b c]])

;(println attempts)

(println (->>
           attempts
           (mapcat order)
           (group-by first)
           (map (fn [[k v]] [k (sort (distinct (map second v)))]))
           (sort-by #(count (second %)))))


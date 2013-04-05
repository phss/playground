(use 'commons)

(def attempts (->> 
                (slurp "files/problem79.txt")
                (clojure.string/split-lines)
                (map seq)
                (distinct)))

(println attempts)
(println (count attempts))

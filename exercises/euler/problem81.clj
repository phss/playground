(use 'commons)

(def matrix (->> 
              "files/problem81.txt"
              (slurp)
              (clojure.string/split-lines)
              (map #(map number-from (clojure.string/split % #",")))))


(println (count matrix))

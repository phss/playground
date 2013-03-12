(use 'commons)

(def message (map clojure.string/trim (clojure.string/split (slurp "files/problem59.txt") #",")))

(println (first message))
(println (last message))

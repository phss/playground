(use 'commons)


(def numbers
  (->>
    "files/problem89.txt"
    (slurp)
    (clojure.string/split-lines)))

(println (last numbers))

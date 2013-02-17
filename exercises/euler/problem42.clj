(def words (map clojure.string/trim (-> "files/problem42.txt"
                                        (slurp)
                                        (clojure.string/split #","))))

(println (first words))
(println (last words))

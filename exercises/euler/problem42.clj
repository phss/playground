(def words (map clojure.string/trim (-> "files/problem42.txt"
                                        (slurp)
                                        (clojure.string/split #","))))

(def char-to-int #(- (int %) (int \A) -1))

(defn word-value [word]
  (let [char-values (map char-to-int word)]
    (reduce + char-values)))

(defn nth-tri [n]
  (int (* 0.5 n (inc n))))

(println (nth-tri 10))

(println (word-value "SKY"))

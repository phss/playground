(def words (map clojure.string/trim (-> "files/problem42.txt"
                                        (slurp)
                                        (clojure.string/split #","))))

(def char-to-int #(- (int %) (int \A) -1))

(defn word-value [word]
  (let [char-values (map char-to-int word)]
    (reduce + char-values)))

(defn nth-tri [n]
  (int (* 0.5 n (inc n))))

(def all-tris (map nth-tri (iterate inc 1)))

(defn word-tri? [word]
  (let [wv (word-value word)
        possible-tris (take-while (partial >= wv) all-tris)]
    (= wv (last possible-tris))))

(time (println (count (filter word-tri? words))))

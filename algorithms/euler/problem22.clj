
(defn name-scores [names]
  2)

(def names (-> "files/problem22.txt"
               (slurp)
               (clojure.string/split #",")))

(time (println (name-scores names)))

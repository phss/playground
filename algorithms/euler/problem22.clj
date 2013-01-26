(def char-to-int #(- (int %) (int \A) -1))

(def sum-name (fn [s] (apply + (map char-to-int s))))

(defn name-scores [names]
  (let [idx-names (map vector (iterate inc 1) (sort names))]
    (println (nth idx-names 937))
    (apply + (map (fn [[idx s]] (* idx (sum-name s))) idx-names))))

(def names (-> "files/problem22.txt"
               (slurp)
               (clojure.string/split #",")))

(time (println (name-scores names)))

(println (sum-name "COLIN"))

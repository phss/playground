
(def max-pan 987654321)
(def min-pan 123456789)

(defn sqrt [n]
  (int (Math/sqrt n)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn nine-pan? [d]
  (= (range 1 10) (sort d)))

(defn pan-prods []
  (for [factor (range 2 (sqrt max-pan))
        cofactor (range (quot min-pan factor) (quot max-pan factor))
        :let [product (* factor cofactor)]
        :when (nine-pan? (concat (digits factor) (digits cofactor) (digits product)))]
    product))

(time (println (count (pan-prods))))

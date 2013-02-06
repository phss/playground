
(def max-pan 987654321)
(def min-pan 123456789)

(defn sqrt [n]
  (int (Math/sqrt n)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn nine-pan? [d]
  (= (range 1 10) (sort d)))

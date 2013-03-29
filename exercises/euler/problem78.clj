(use 'commons)

(defn k-partitions [k n]
  (cond
    (> k n) 0
    (= k n) 1
    :else (+ (k-partitions (inc k) n) (k-partitions k (- n k)))))

(defn partitions [n]
  (let [ks (range 1 (inc (floor (/ n 2))))]
    (inc (reduce + (map (fn [k] (k-partitions k (- n k))) ks)))))

(def all-partitions (map partitions (iterate inc 1)))

(doseq [n (iterate inc 1)]
  (println n (partitions n)))

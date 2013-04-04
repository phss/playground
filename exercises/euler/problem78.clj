(use 'commons)

(declare k-fast)

(defn k-partitions [k n]
  (cond
    (> k n) 0
    (= k n) 1
    :else (+ (k-fast (inc k) n) (k-fast k (- n k)))))

(def k-fast (memoize k-partitions))

(defn partitions [n]
  (let [ks (range 1 (inc (floor (/ n 2))))]
    (inc (reduce + (map (fn [k] (k-fast k (- n k))) ks)))))

(def all-partitions (map partitions (iterate inc 1)))

(time (println (first (filter #(divisible? % 100) all-partitions))))

(use 'commons)

; Recursive
(declare k-fast)

(defn k-partitions [k n]
  (cond
    (> k n) (bigint 0)
    (= k n) (bigint 1)
    :else (+ (k-fast (inc k) n) (k-fast k (- n k)))))

(def k-fast (memoize k-partitions))

(defn partitions [n]
  (let [ks (range 1 (inc (floor (/ n 2))))]
    (inc (reduce + (map (fn [k] (k-fast k (- n k))) ks)))))

(def all-partitions (map partitions (iterate inc 1)))

;(time (println (partitions 100)))

; Generative

(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5))) 

(defn gen-seq 
  ([] (concat [0] (gen-seq 1)))
  ([n] (concat [n (- n)] (lazy-seq (gen-seq (inc n))))))

(def general-pents (vec (take 10000 (map nth-pent (gen-seq)))))

(declare p-fast)

(defn p [n]
  (let [pents (take-while #(<= % n) (rest general-pents))
        signs (cycle [1 1 -1 -1])
        composing-ps (map (fn [k s] (* s (p-fast (- n k))) ) pents signs)]
    (if (zero? n) 
      (bigint 1)
      (reduce + composing-ps))))

(def p-fast (memoize p))

(time (println (first (filter #(divisible? (second %) 1000000) (map (fn [i] [i (p-fast i)]) (iterate inc 1))))))

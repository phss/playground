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

;(time (println (first (filter #(divisible? % 100000) all-partitions))))

; Generative

(defn nth-pent [n]
  (int (* n (- (* 3 n) 1) 0.5))) 

(defn gen-seq 
  ([] (concat [0] (gen-seq 1)))
  ([n] (concat [n (- n)] (lazy-seq (gen-seq (inc n))))))

(def general-pents (vec (take 10000 (map nth-pent (gen-seq)))))

(println (last general-pents))

(defn p [n]
  (let [idx (map #(nth general-pents %) (range 1 (inc n)))
        composing-ps (map #(p (- n %)) idx)]
    (cond
      (> 0 n) 0
      (zero? n) 1
      :else composing-ps)))

(println (p 70))

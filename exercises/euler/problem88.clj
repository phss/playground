(use 'commons)
(use 'clojure.test)

(defn product-sum? [numbers]
  (let [product (reduce * numbers)
        sum (reduce + numbers)]
    (= product sum)))

(is (product-sum? [1 2 3]))
(is (not (product-sum? [3 3 3])))

(defn k-product-sum [numbers]
  (let [product (reduce * numbers)
        sum (reduce + numbers)]
    (+ (count numbers) (- product sum))))

(defn next-permutations [nums]
  (for [n nums
        nx (range (last n) 10)]
    (conj n nx)))

(defn all-permutations
  ([] (all-permutations (map (fn [n] [n]) (range 2 10))))
  ([nums]
    (let [next-nums (next-permutations nums)]
      (concat next-nums (lazy-seq (all-permutations next-nums))))))

(println (take 100 (all-permutations)))

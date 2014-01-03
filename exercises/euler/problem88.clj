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

(defn sum-min-product-sum [lower upper]
  (loop [ks {} nums (all-permutations)]
    (if (= (count ks) (dec upper))
      ks
      (let [comb (first nums)
            prod (reduce * comb)
            k (k-product-sum comb)]
        (if (or (not (contains? ks k)) (< prod (ks k)))
          (recur (assoc ks k prod) (next nums))
          (recur ks (next nums)))))))


(def prod-sums (map (fn [comb] {(k-product-sum comb) (reduce * comb)}) (take 12001 (all-permutations))))

(def smallest-prod-sums (reduce (partial merge-with min) prod-sums))

(println (reduce + (set (vals (select-keys smallest-prod-sums (range 2 12001))))))

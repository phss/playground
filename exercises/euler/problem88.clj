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

(println (k-product-sum [2 3]))
(println (k-product-sum [2 2 2]))

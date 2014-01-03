(use 'commons)
(use 'clojure.test)

(defn product-sum? [numbers]
  (let [product (reduce * numbers)
        sum (reduce + numbers)]
    (= product sum)))

(is (product-sum? [1 2 3]))
(is (not (product-sum? [3 3 3])))



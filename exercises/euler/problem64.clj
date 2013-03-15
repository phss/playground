(use 'commons)
(use 'clojure.test)

(defn sqrt-period [n]
  nil)

(is (= [2] (sqrt-period 2)))
(is (= [1 1 1 4] (sqrt-period 7)))
(is (= [3 6] (sqrt-period 11)))

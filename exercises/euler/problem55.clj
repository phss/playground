(use 'commons)
(use 'clojure.test)

(defn lychrel? [n]
  false)

(is (= false (lychrel? 47)))
(is (= false (lychrel? 349)))
(is (= true (lychrel? 196)))


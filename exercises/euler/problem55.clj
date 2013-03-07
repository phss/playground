(use 'commons)
(use 'clojure.test)

(defn lychrel? [n]
  false)

(is (= true (lychrel? 47)))
(is (= true (lychrel? 349)))
(is (= false (lychrel? 196)))


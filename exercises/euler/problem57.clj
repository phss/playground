(use 'commons)
(use 'clojure.test)

(defn expanded-to [n]
  (loop [e 2 c n]
    (if (= c 1)
      (+ 1 (/ 1 e))
      (recur (+ 2 (/ 1 e)) (dec c)))))

(is (= (/ 3 2) (expanded-to 1)))
(is (= (/ 41 29) (expanded-to 4)))

(use 'commons)
(use 'clojure.test)

(defn pow [n e]
  (loop [p (bigint n) c e]
    (if (= c 1)
      p
      (recur (* p n) (dec c)))))

(is (= 59049 (pow 3 10)))

(println (digits-from (pow 100 100)))

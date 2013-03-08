(use 'commons)
(use 'clojure.test)

(defn pow [n e]
  (loop [p (bigint n) c e]
    (if (= c 1)
      p
      (recur (* p n) (dec c)))))

(is (= 59049 (pow 3 10)))

(def all-powers (for [a (range 1 101)
                      b (range 1 101)]
                  (reduce + (digits-from (pow a b)))))

(println (last (sort all-powers)))

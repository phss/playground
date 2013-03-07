(use 'commons)
(use 'clojure.test)

(defn reverse-and-add [n]
  (let [r (bigint-from (reverse (digits-from n)))]
    (+ r n)))

(defn lychrel? [number]
  (loop [n (reverse-and-add number) c 1]
    (cond
      (palindrome? n) false
      (= c 50) true
      :else (recur (reverse-and-add n) (inc c)))))

(is (= false (lychrel? 47)))
(is (= false (lychrel? 349)))
(is (= true (lychrel? 196)))

(println (count (filter lychrel? (range 1 10001))))

(use 'commons)
(use 'clojure.test)

(def primes (primes-up-to 1000000))

(defn mask-from [a b]
  (let [digits-a (digits-from a)
        digits-b (digits-from b)]
    (if (= (count digits-a) (count digits-b))
      (loop [mask [] da digits-a db digits-b]
        (cond
          (empty? da) (apply str mask)
          (= (first da) (first db)) (recur (conj mask (first da)) (rest da) (rest db))
          :else (recur (conj mask "*") (rest da) (rest db))))
      nil)))

(is (= "1234" (mask-from 1234 1234)))
(is (= "1*34" (mask-from 1234 1334)))
(is (= "1**4" (mask-from 1224 1334)))
(is (= "****" (mask-from 2222 3333)))
(is (= nil (mask-from 123 12)))
(is (= nil (mask-from 1234 1344)))

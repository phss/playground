(use 'commons)
(use 'clojure.test)

(def primes (primes-up-to 1000000))

(defn mask-from [a b]
  (loop [mask [] da (digits-from a) db (digits-from b)]
    (cond
      (and (empty? da) (empty? db)) (apply str mask)
      (or (empty? da) (empty? db)) nil
      (= (first da) (first db)) (recur (conj mask (first da)) (rest da) (rest db))
      :else (recur (conj mask "*") (rest da) (rest db)))))


(is (= "1234" (mask-from 1234 1234)))
(is (= "1*34" (mask-from 1234 1334)))
(is (= "1**4" (mask-from 1224 1334)))
(is (= "****" (mask-from 2222 3333)))
(is (= nil (mask-from 123 12)))
(is (= nil (mask-from 1234 1344)))

(use 'commons)
(use 'clojure.test)

(def primes (vec (take 1000 all-primes)))

(defn concat-primes? [p1 p2]
  (let [p1d (digits-from p1)
        p2d (digits-from p2)]
    (and (prime? (number-from (concat p1d p2d)))
         (prime? (number-from (concat p2d p1d))))))

(is (= true (concat-primes? 3 7)))
(is (= true (concat-primes? 3 109)))
(is (= true (concat-primes? 3 673)))
(is (= true (concat-primes? 7 109)))
(is (= true (concat-primes? 7 673)))
(is (= true (concat-primes? 109 673)))
(is (= false (concat-primes? 5 673)))

(def concats (for [i (range (dec (count primes)))
                   j (range (inc i) (count primes))
                   :let [pi (nth primes i)
                         pj (nth primes j)]
                   :when (concat-primes? pi pj)]
               [pi pj]))

(println (filter (fn [[k v]] (> v 3)) (frequencies (map first concats))))

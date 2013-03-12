(use 'commons)
(use 'clojure.test)
(use 'clojure.set)
(use 'clojure.math.combinatorics)

(def primes (vec (primes-up-to 1000)))

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


(def sets (apply merge (map (fn [p] {p (conj (set (filter (partial concat-primes? p) primes)) p)}) primes)))

(println (count sets))

(defn pair-set [ss]
  (let [ps (apply intersection (map second ss))]
    ps))

(def pair-sets (filter #(= 4 (count (pair-set %))) (combinations sets 3)))

(time (println (count pair-sets)))

(println (pair-set (first pair-sets)))

(def elems (map pair-set pair-sets))

(time (println (take 20 (sort-by first (map (fn [e] [(reduce + e) e]) elems)))))

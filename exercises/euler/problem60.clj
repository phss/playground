(use 'commons)
(use 'clojure.test)
(use 'clojure.set)
(use 'clojure.math.combinatorics)

(def primes (vec (rest (primes-up-to 1000))))

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

(def init-prime-sets (apply merge (map (fn [p] { p #{p} }) primes)))

(def prime-combinations (for [pi primes 
                              pj primes 
                              :while (> pi pj)
                              :when (concat-primes? pi pj)] 
                          [pi pj]))

(defn update-sets [sets pi pj]
  (-> sets
      (update-in [pi] conj pj)
      (update-in [pj] conj pi)))

(defn prime-set? [prime-sets s n]
  (let [combs (combinations (map prime-sets s) n)
        intersects (map #(apply intersection %) combs)]
    (first (filter #(= n (count %)) intersects))))

(defn prime-set-with-size [n]
  (loop [ps [] combs prime-combinations]
    (let [[pi pj] (first combs)
          new-ps (update-sets ps pi pj)
          prime-set (first (filter #(= n (count %)) new-ps))]
      (cond 
        prime-set prime-set
        (empty? combs) nil
        :else (recur new-ps (rest combs))))))

(time (println (prime-set-with-size 4)))

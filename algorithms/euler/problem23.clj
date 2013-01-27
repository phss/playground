
(def upper-bound 20161)

(defn sum-of-divisors [n]
  (let [divisors (fn [n] (filter #(zero? (rem n %)) (range 1 n)))]
    (apply + (divisors n))))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(def abundants (filter abundant? (range 12 upper-bound)))

(def non-abundants (for [n (range 1 100) 
                         :when (not-any? (fn [a] (let [f (- n a)] (and (> f 0) (some #{f} abundants)))) abundants)]
                     n))

;(println (apply + non-abundants))

(println (take 50 abundants))
(println (take 50 non-abundants))

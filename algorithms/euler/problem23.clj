

(defn sum-of-divisors [n]
  (let [divisors (fn [n] (filter #(zero? (rem n %)) (range 1 n)))]
    (apply + (divisors n))))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(def abundants (filter abundant? (range 12 28123)))

(def non-abundants (for [n (range 1 1000) 
                         :when (not-any? (fn [a] (let [f (- n a)] (and (> f 0) (some #{f} abundants)))) abundants)]
                     n))

(println (apply + non-abundants))

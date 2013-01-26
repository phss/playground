

(defn sum-of-divisors [n]
  (let [divisors (fn [n] (filter #(zero? (rem n %)) (range 1 n)))]
    (apply + (divisors n))))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(def abundants (filter abundant? (range 12 28123)))

(def non-abundants (for [n (range 1 28123) 
                         a abundants
                         :let [f (- n a)]
                         :when (and (> f 0) (not-any? #{f} abundants))]
                     n))

(println (apply + non-abundants))


(def upper-bound 28123)

(defn sum-of-divisors [n]
  (let [divisors (fn [n] (filter #(zero? (rem n %)) (range 1 n)))]
    (apply + (divisors n))))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(def abundants (filter abundant? (range 12 upper-bound)))

(def sum-abundants (for [a abundants b abundants] (+ a b)))

(count sum-abundants)

(println (apply + (remove (fn [n] (some #{n} sum-abundants)) (range upper-bound))))



(def upper-bound 28123)

(defn sum-of-divisors [n]
  (let [divisors (fn [n] (filter #(zero? (rem n %)) (range 1 n)))]
    (apply + (divisors n))))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(def abundants (filter abundant? (range 12 upper-bound)))

(def sum-abundants (distinct (for [a abundants b abundants :let [s (+ a b)] :while (and (<= b a) (< s upper-bound))] s)))

(count sum-abundants)

(println (apply + (remove (fn [n] (some #{n} sum-abundants)) (range upper-bound))))


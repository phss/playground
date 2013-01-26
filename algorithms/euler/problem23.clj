

(defn sum-of-divisors [n]
  (let [divisors (fn [n] (filter #(zero? (rem n %)) (range 1 n)))]
    (apply + (divisors n))))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(println (filter abundant? (range 12 28123)))


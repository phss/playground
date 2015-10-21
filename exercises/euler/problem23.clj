(ns problem23)

(def upper-bound 28123)

(defn sum-of-divisors [n]
  (->> (range 1 (inc (/ n 2)))
       (filter #(zero? (rem n %)))
       (apply +)))

(defn abundant? [n]
  (< n (sum-of-divisors n)))

(def abundants (filter abundant? (range 12 upper-bound)))

(def sum-abundants
  (distinct
    (for [a abundants b abundants
          :let [s (+ a b)]
          :while (and (<= b a) (< s upper-bound))]
      s)))

(println (apply + (remove (fn [n] (some #{n} sum-abundants)) (range upper-bound))))


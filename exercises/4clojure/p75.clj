(ns fourclojure.p75)

(defn totient [x]
  (let [divisible? #(zero? (rem %1 %2))
        gcd (fn [a b] (last (filter #(and (divisible? a %) (divisible? b %)) (range 1 (inc b)))))]
    (if (= 1 x) 1 (count (filter #(= 1 (gcd x %)) (range 1 x))))))

(println (totient 40))

(println (totient 99))

(println (totient 1))
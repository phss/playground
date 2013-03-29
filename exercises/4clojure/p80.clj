(ns fourclojure.p80)


(defn per? [n]
  (let [divisors (filter #(zero? (rem n %)) (range 1 n))]
    (= n (reduce + divisors))))

(per? 8128)
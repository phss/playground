(ns eulerclj.problem3)

;The prime factors of 13195 are 5, 7, 13 and 29.
;
;What is the largest prime factor of the number 600851475143 ?

(defn first-factor [num]
  (first (filter #(zero? (mod num %)) (iterate inc 2))))

(defn prime-factors [num]
  (loop [n num factors []]
    (if (= n 1)
      factors
      (let [factor (first-factor n)]
        (recur (quot n factor) (cons factor factors))))))

(time (prime-factors 600851475143))
(ns eulerclj.problem3)

;The prime factors of 13195 are 5, 7, 13 and 29.
;
;What is the largest prime factor of the number 600851475143 ?

(defn first-factor [num]
  (if (= 1 num)
    nil
    (first (filter #(zero? (mod num %)) (iterate inc 2)))))

(defn prime-factors [num]
  (loop [n num factors []]
    (if (= n 1)
      factors
      (let [factor (first-factor n)]
        (recur (quot n factor) (cons factor factors))))))

(time (println (prime-factors 600851475143)))

; A little fancier

(defn prime-factors2 [num]
  (let [next-factor (fn [[n f]] [(quot n f) (first-factor (quot n f))])]
  (map second (take-while #(> (first %) 1) (iterate next-factor [num (first-factor num)])))))

(time (println (prime-factors2 600851475143)))
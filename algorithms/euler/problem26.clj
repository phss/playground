
(defn recurring-cycle [divisor]
  (loop [dividend 1 qr []]
    (let [q (quot dividend divisor)
          r (rem dividend divisor)
          idx (.indexOf qr [q r])]
      (if (>= idx 0)
        (- (count qr) idx)
        (recur (* 10 r) (conj qr [q r]))))))

(println (recurring-cycle 7))
(println (recurring-cycle 6))

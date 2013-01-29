
(defn recurring-cycle [divisor]
  (loop [dividend 1 qr []]
    (let [q (quot dividend divisor)
          r (rem dividend divisor)]
      (println [q r])
      (if (= q 7)
        qr
        (recur (* 10 r) (conj qr [q r]))))))

(println (recurring-cycle 7))

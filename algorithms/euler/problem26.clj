
(defn recurring-cycle [divisor]
  (loop [dividend 1 qr []]
    (let [q (quot dividend divisor)
          r (rem dividend divisor)
          idx (.indexOf qr [q r])]
      (if (>= idx 0)
        (- (count qr) idx)
        (recur (* 10 r) (conj qr [q r]))))))

(defn max-cycle [numbers]
  (let [ncycles (map (fn [n] [n (recurring-cycle n)]) numbers)]
    (last (sort-by second ncycles))))

(time (println (max-cycle (range 1 1000))))

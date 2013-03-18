(use 'commons)

(def goal (/ 3 7))

(defn closest-rpf [d]
  (let [nums (map #(/ % d) (filter #(gcd % d) (range 1 (/ d 2))))]
    (last (take-while #(> goal %) nums))))

(time (println (closest-rpf 9876543))) ; 4232804/9876543

(defn rpf-closest-to-goal [n]
  5)

(time (println (rpf-closest-to-goal 9)))

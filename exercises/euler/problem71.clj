(use 'commons)

(def goal (/ 3 7))

(defn closest-rpf [d]
  (let [candidates (filter #(gcd % d) (iterate dec (int (/ d 2))))
        ratios (map (fn [n] [n (/ n d)]) candidates)]
    (first (drop-while (fn [[n r]] (< goal r)) ratios))))

(time (println (closest-rpf 9876543))) ; 4232804/9876543

(defn rpf-closest-to-goal [n]
  5)

(time (println (rpf-closest-to-goal 9)))

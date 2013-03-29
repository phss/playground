(use 'commons)

(def goal (/ 3 7))

(defn closest-rpf [d]
  (let [closest-n (dec (floor (* d goal)))]
    (if (gcd closest-n d)
      [closest-n d (/ closest-n d)]
      nil)))

(time (println (closest-rpf 9876543))) ; 4232804/9876543

(def closest-rpfs (map closest-rpf (remove #{7} (range 2 1000001))))

(time (println (last (sort-by last closest-rpfs))))


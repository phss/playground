(use 'commons)

(defn rpf-for [n]
  (let [pf (frequencies (prime-factors n))
        perms (fn [[p f]] (* (dec p) (pow p (dec f))))]
    (reduce * (map perms pf))))

(time (println (reduce + (map rpf-for (range 2 1000001)))))

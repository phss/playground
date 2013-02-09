;(defn divisors [n]
;  (mapcat (fn [f] [f (/ n f)]) (filter #(zero? (rem n %)) (range 1 (int (Math/sqrt n))))))

(defn divisors [n]
  (filter #(zero? (rem n %)) (range 1 n)))

(defn sum-of-divisors [n]
  (apply + (divisors n)))

(defn amicable-sum [n]
  (let [sods (for [i (range n)] [i (sum-of-divisors i)])
        amicable? (fn [[i sod]] (and (not= i sod) (< sod n) (= i (second (nth sods sod)))))
        amicables (filter amicable? sods)]
    (apply + (map second amicables))))  

(time (println (amicable-sum 10000)))

;(println (divisors 57))
;(println (divisors 79))


(defn sum-of-divisors [n]
  (let [divisors  (mapcat (fn [f] [f (/ n f)])
                          (filter #(zero? (rem n %)) (range 1 (inc (Math/sqrt n)))))]
    (apply + divisors)))

;(time (println (map divisors (range 1 10001))))
;
(println (sum-of-divisors 220))
(println (sum-of-divisors 284))

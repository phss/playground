
(defn triangle-with-divisors [n]
  (let [triangle-numbers (map #(apply + (range (inc %))) (iterate inc 1))
        n-divisors? (fn [t] (< n (count (filter #(zero? (rem t %)) (range 1 (inc t))))))]
    (first (filter n-divisors? triangle-numbers))))


(time (println (triangle-with-divisors 500)))

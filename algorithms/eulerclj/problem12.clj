
(def triangle-numbers (map #(int (* % (inc %) 0.5)) (iterate inc 1)))

(defn divisors [n]
  (filter #(zero? (rem n %)) (range 1 (inc n))))

(defn triangle-with-divisors [n]
  (let [n-divisors? (fn [t] (< n (count (divisors t))))]
    (first (filter n-divisors? triangle-numbers))))


(time (println (triangle-with-divisors 20)))

(doseq [n (take 1 triangle-numbers)
        :let [divs (divisors n)]]
  (println n (count divs) divs))

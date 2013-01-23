
(def triangle-numbers (map #(int (* % (inc %) 0.5)) (iterate inc 1)))

(defn divisors [n]
  (mapcat
    (fn [f] [f (/ n f)])
    (filter #(zero? (rem n %)) (range 1 (inc (Math/sqrt n))))))

(defn triangle-with-divisors [n]
  (let [n-divisors? (fn [t] (< n (count (divisors t))))]
    (first (filter n-divisors? triangle-numbers))))

(time (println (triangle-with-divisors 500)))


(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (not-any? (partial divisible? n) (range 2 (inc (int (Math/sqrt n)))))))

(def primes (filter prime? (range 2 1000000)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn to-num [d]
  (Integer/parseInt (apply str d)))

(defn rotations [n]
  (let [d (digits n)]
    (map (fn [i]
           (let [[bef aft] (split-at i d)]
             (to-num (concat aft bef)))) 
         (range (count d)))))

(defn circular-prime? [n]
  (every? #(prime? %) (rotations n)))

(time (println (count (filter circular-prime? primes))))

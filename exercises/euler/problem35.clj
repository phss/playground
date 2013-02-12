
(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (not-any? (partial divisible? n) (range 2 (inc (Math/sqrt n))))))

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

(def primes (filter prime? (range 2 1000000)))

(println (take 50 primes))

(time (println (first (map rotations primes))))

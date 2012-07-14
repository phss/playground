(ns eulerclj.problem1)

(def cap 10000000)

; Brute force
(defn- divisible-by-3-or-5? [n]
  (or (zero? (mod n 3)) (zero? (mod n 5))))

(defn sum-of-multiples-below [cap]
  (let [numbers (for [n (range cap) :when (divisible-by-3-or-5? n)] n)]
    (reduce + numbers)))

(time (println (sum-of-multiples-below cap)))

; Smarter

(defn- sum-divisibles [cap n]
  (let [p (quot cap n)]
    (/ (* n p (+ p 1)) 2)))

(defn calculated-some-of-multiples [cap]
  (let [adjusted-cap (- cap 1)]
    (+ (sum-divisibles adjusted-cap 3) 
       (sum-divisibles adjusted-cap 5) 
       (- (sum-divisibles adjusted-cap 15)))))

(time (println (calculated-some-of-multiples cap)))
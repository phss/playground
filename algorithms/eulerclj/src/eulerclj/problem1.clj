(ns eulerclj.problem1)

;If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;
;Find the sum of all the multiples of 3 or 5 below 1000.

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
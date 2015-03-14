; Given an array with positive and negative numbers (e.g. [1, 0, -2, 3, 2, 2, -10])
; calculate the largest sum of consecutive numbers. On the previous example, it would be 7


(defn largest-consecutive-sum [numbers]
  (loop [[number & remaining] numbers current-sum 0 largest-sum (apply max numbers)]
    (if (nil? number)
      largest-sum
      (let [new-sum (+ current-sum number)]
        (recur remaining (max 0 new-sum) (max largest-sum new-sum))))))


(println (largest-consecutive-sum [1, 0, -2, 3, 2, 2, -10])) ; => 7
(println (largest-consecutive-sum [-1, -2, 3, -2, -2, 10])) ; => 10, edge case: last number is largest
(println (largest-consecutive-sum [10, -20, 3, -2, 2, -10])) ; => 10, edge case: first number is largest
(println (largest-consecutive-sum [7, -2, 3, -2, 2, -10])) ; => 8, sum goes up and down
(println (largest-consecutive-sum [-32, -2, -30, -1, -5, -6])) ; => -1, only negatives 

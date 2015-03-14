; Given an array with positive and negative numbers (e.g. [1, 0, -2, 3, 2, 2, -10])
; calculate the largest sum of consecutive numbers. On the previous example, it would be 7


(defn largest-consecutive-sum [numbers]
  (loop [nums numbers current-sum 0 largest-sum (apply max numbers)]
    (if (empty? nums)
      largest-sum
      (let [new-sum (+ current-sum (first nums))]
        (recur (rest nums) 
               (if (> 0 new-sum) 0 new-sum)
               (if (> new-sum largest-sum) new-sum largest-sum))))))


(println (largest-consecutive-sum [1, 0, -2, 3, 2, 2, -10])) ; => 7
(println (largest-consecutive-sum [-1, -2, 3, -2, -2, 10])) ; => 10, edge case: last number is largest
(println (largest-consecutive-sum [10, -20, 3, -2, 2, -10])) ; => 10, edge case: first number is largest
(println (largest-consecutive-sum [7, -2, 3, -2, 2, -10])) ; => 8, sum goes up and down
(println (largest-consecutive-sum [-32, -2, -30, -1, -5, -6])) ; => -1, only negatives 

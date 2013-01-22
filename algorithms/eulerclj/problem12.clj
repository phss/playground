
(def triangle-numbers (map #(apply + (range (inc %))) (iterate inc 1)))

(time (println (take 50 triangle-numbers)))

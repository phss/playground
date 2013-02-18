(use 'commons)
(use 'clojure.math.combinatorics)

(def all-pans-digits (filter #(not= 0 (first %)) (permutations (range 10))))

(defn weird-divisibility? [digits]
  (let [div-groups [[[1 2 3] 2] [[2 3 4] 3] [[3 4 5] 5]
                    [[4 5 6] 7] [[5 6 7] 11] [[6 7 8] 13] [[7 8 9] 17]]]
    (every? (fn [[group divisor]]
              (let [n (number-from (map (partial nth digits) group))]
                (divisible? n divisor))) 
            div-groups)))

(def weirdly-divisible-pans (filter weird-divisibility? all-pans-digits))

(println (reduce + (map long-from weirdly-divisible-pans)))

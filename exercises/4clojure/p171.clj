(ns fourclojure.p171)

(defn intervals [col]
  (let [c (distinct (sort col))]
    (loop [rem (rest c) g [(first c)] int []]
	    (let [l (last g)
	          n (first rem)]
	      (cond
	        (empty? rem) (conj int [(first g) (last g)])
	        (= (inc l) n) (recur (rest rem) (conj g n) int)
	        :else (recur (rest rem) [n] (conj int [(first g) (last g)])))))))



(defn split-by-pair [f col]
  (reduce (fn [acc e]
            (let [l (last (last acc))]
              (if (f l e)                
                (concat acc [[e e]])
                (concat (butlast acc) [[(first (last acc)) e]])))) 
          [[(first col) (first col)]] (rest col)))

(defn intervals2 [col]
  (split-by-pair #(not= (inc %1) %2) (distinct (sort col))))

(println (intervals2 [10 9 8 1 2 3 5]))
(println (intervals2 [1 1 1 1]))
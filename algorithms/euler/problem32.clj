
(def max-pan 987654321)
(def min-pan 123456789)

(defn sqrt [n]
  (int (Math/sqrt n)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn nine-pan? [d]
  (= (range 1 10) (sort d)))

(defn perms [col]
  (loop [p (map vector col) n 1]
    (if (= n (count col))
      p
      (recur (reduce (fn [ps ]) 
                     [] p) 
             (inc n)))))


(println (take 5 (perms (range 1 10))))

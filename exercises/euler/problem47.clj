(use 'commons)

(def factors-seq (filter (fn [[n factors]] (> (count factors) 1)) 
                         (map (fn [n] [n (distinct (prime-factors n))]) 
                              (iterate inc 10))))

(def consecutives (partition 3 1 factors-seq))

(defn unique-factors? [group]
  (let [all-factors (mapcat second group)]
    (= all-factors (distinct all-factors))))

(println (first (filter unique-factors? consecutives)))

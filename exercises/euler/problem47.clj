(use 'commons)

(def size 4)

(def factors-seq (filter (fn [[n factors]] (> (count factors) 1)) 
                         (map (fn [n] [n (distinct (prime-factors n))]) 
                              (iterate inc 10))))

(def consecutives (filter (fn [group]
                            (let [first-n (ffirst group)
                                  last-n  (first (last group))]
                              (= (dec size) (- last-n first-n)))) 
                          (partition size 1 factors-seq)))

(defn with-n-factors? [group]
  (let [factors (map second group)]
    (every? #(= size (count %)) factors)))

(println (first (filter with-n-factors? consecutives)))


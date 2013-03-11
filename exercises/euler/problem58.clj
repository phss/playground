(use 'commons)

(defn lazy-diag
  ([] (lazy-diag 2 1))
  ([n l] (let [diag (map #(+ l (* n %)) [1 2 3 4])]
           (cons diag (lazy-seq (lazy-diag (+ n 2) (last diag)))))))

(def percents (reductions (fn [[p n] d] [(+ p (count (filter prime? d))) (+ n 4)]) [0 1] (lazy-diag)))

(println (count (take-while (fn [[p n]] (> (/ p n) 0.1)) (rest percents))))


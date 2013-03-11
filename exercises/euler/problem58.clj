(use 'commons)

(defn lazy-diag
  ([] (lazy-diag 2 1))
  ([n l] (let [diag (map #(+ l (* n %)) [1 2 3 4])]
           (cons diag (lazy-seq (lazy-diag (+ n 2) (last diag)))))))

(println (take 5 (lazy-diag)))


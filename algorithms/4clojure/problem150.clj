(defn pals [n]
  (letfn [(nextp [x] 
            x)] 
    (cons n (lazy-seq (pals (inc n))))))


(println (take 26  (pals 0)))

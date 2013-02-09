
(defn mating-prob [k m n]
  (let [total (+ k m n)
        half #(/ % 2)]
    (+ (/ k total)
       (* (/ n total) (/ k (dec total)))
       (* (/ (half m) total) (/ k (dec total)))
       (/ (half m) total)
       (* (/ n total) (/ (half m) (dec total)))
       (* (/ (half m) total) (/ (half (dec m)) (dec total))))))

(println (float (mating-prob 29 17 25)))

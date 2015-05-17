(ns converter)

(def zero (int \0))
(def letter-a (int \a))

(defn hex-to-decimal [s]
  (let [letter? #(>= (int %) letter-a)
        offset #(if (letter? %) (- letter-a 10) zero)
        to-decimal #(- (int %) (offset %))]
    (map (fn [[a b]] (+ (* a 16) b)) (partition 2 (map to-decimal s)))))

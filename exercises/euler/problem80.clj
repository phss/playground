(use 'commons)

(defn digit-pair [n]
  (let [digits (digits-from n)
        padded-digits (if (odd? (count digits)) (cons 0 digits) digits)]
    (partition 2 padded-digits)))

(def integer-digit-pairs (map digit-pair (iterate inc 1)))

(println (nth integer-digit-pairs 102))

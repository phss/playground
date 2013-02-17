(use 'commons)

(defn pandigital-0-9? [n]
  (let [digits (digits-from n)]
    (= (range (count digits)) (sort digits))))

(def all-pans (filter pandigital-0-9? (range 1023456789 9876543211)))

(println (count all-pans))

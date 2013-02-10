(defn number [a b]
  (Integer/parseInt (str a b)))

(def fracs (for [canc (range 1 10)
                 n (range 1 9)
                 d (range (inc n) 10)
                 :let [simple (/ n d)
                       two-digits-1 (/ (number canc n) (number d canc))
                       two-digits-2 (/ (number n canc) (number canc d))]
                 :when (or (= simple two-digits-1) (= simple two-digits-2))]
             [canc n d]))

(println fracs)

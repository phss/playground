(ns problem100)

(defn valid? [t b]
  (= 1/2 (* (/ b t) (/ (dec b) (dec t)))))

(valid? 120 85)

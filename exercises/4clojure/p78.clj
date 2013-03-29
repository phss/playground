(ns fourclojure.p78)

(defn tramp [f v]
  (loop [res (f v)]
    (if (fn? res)
      (recur (res))
      res)))

(println (letfn [(triple [x] #(sub-two (* 3 x)))
          (sub-two [x] #(stop?(- x 2)))
          (stop? [x] (if (> x 50) x #(triple x)))]
    (tramp triple 2)))

(println ((letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
          (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
    (map (partial tramp my-even?) (range 6)))))
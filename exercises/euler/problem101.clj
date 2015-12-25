(ns problem101
  (:require  [clojure.math.numeric-tower :as math]))

(defn cube [n]
  (math/expt n 3))

(defn polynomial [n]
  (let [ncol (map (partial math/expt n) (range 11))
        signs (cycle [+ -])]
    (reduce (fn [sum [sign n]] (sign sum n))
            0
            (map vector signs ncol))))

(println
  (->>
    (range 1 12)
    (map polynomial)))

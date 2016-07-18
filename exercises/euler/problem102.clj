(ns problem102)

(def positive-tri [[-340 495] [-153 -910] [835 -947]])
(def negative-tri [[-175  41] [-421 -714] [574 -645]])

(defn tri-contain-origin? [tri]
  true)

(println (tri-contain-origin? positive-tri))
(println (tri-contain-origin? negative-tri))

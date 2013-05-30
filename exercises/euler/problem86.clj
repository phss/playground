(use 'commons)

(defn integer-shortest-route?
  [d1 d2 d3]
  (let [distance (sqrt (+ (pow2 d1) (pow2 (+ d2 d3))))]
    (no-decimal? distance)))

(println (integer-shortest-route? 6 5 3))
(println (integer-shortest-route? 5 6 3))

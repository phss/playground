(use 'commons)

(defn integer-shortest-route?
  [d1 d2 d3]
  (let [distance (sqrt (+ (pow2 d1) (pow2 (+ d2 d3))))]
    (no-decimal? distance)))

;(println (integer-shortest-route? 6 5 3))
;(println (integer-shortest-route? 5 6 3))

(defn integer-routes-for
  [m]
  (for [d2 (range 1 (inc m))
        d3 (range 1 (inc d2))
       :when (integer-shortest-route? m d2 d3)]
    [m d2 d3]))

;(println (integer-routes-for 6))

(def boundary 2000)
;(def boundary 1000000)

(time (println
  (loop [m 1 routes 0]
    (if (>= routes boundary)
      [(dec m) routes]
      (recur (inc m) (+ routes (count (integer-routes-for m))))))))

(use 'commons)

(defn distance [a b]
  (let [[ax ay] a
        [bx by] b]
    (sqrt (+ (pow2 (- ax bx)) (pow2 (- ay by))))))

(defn pyth-right-triangle? [p q]
  (let [o [0 0]
        distances (map (fn [ps] (distance (first ps) (second ps))) [[o p] [o q] [p q]])
        [a b c] (sort distances)]
    (and (not= o p) (not= o q) (not= p q)
         (almost= (pow2 c) (+ (pow2 a) (pow2 b))))))

(def upper 51)

(def right-angles 
  (for [px (range upper) qy (range upper)
        qx (range px upper) py (range qy upper)
        :let [p [px py] q [qx qy]]
        :when (pyth-right-triangle? p q)]
    [p q]))

(println (count right-angles))

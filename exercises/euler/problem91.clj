(use 'commons)

(defn right-triangle? [p q]
  (let [[px py] p
        [qx qy] q
        not-origin? #(not= [0 0] %)
        lower-left-tri? (and (zero? px) (zero? qy) (> qx px))
        upper-left-tri? (and (zero? px) (= py qy) (> qx px))
        lower-right-tri? (and (zero? qy) (= px qx) (> py qy))
        middle-tri? (or (and (zero? qy) (even? qx) (= px py (/ qx 2)))
                        (and (zero? px) (even? py) (= qx qy (/ py 2))))]
    (and (not-origin? p) (not-origin? q)
         (or lower-left-tri?  upper-left-tri?  lower-right-tri? middle-tri?))))

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

;(println (pyth-right-triangle? [0, 1] [1, 0]))

(def upper 51)

(def right-angles 
  (for [px (range upper) qy (range upper)
        qx (range px upper) py (range qy upper)
        :let [p [px py] q [qx qy]]
        :when (pyth-right-triangle? p q)]
    [p q]))

;(println right-angles)
(println (count right-angles))

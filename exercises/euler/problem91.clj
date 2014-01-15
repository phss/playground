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

(def upper 3)

(def right-angles 
  (for [px (range upper) py (range upper)
        qx (range upper) qy (range upper)
        :let [p [px py] q [qx qy]]
        :when (right-triangle? p q)]
    [p q]))

(println right-angles)
(println (count right-angles))

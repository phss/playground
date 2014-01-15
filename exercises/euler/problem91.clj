(defn right-triangle? [p q]
  (let [[px py] p
        [qx qy] q
        not-origin? #(not= [0 0] %)]
    (and (not-origin? p) (not-origin? q)
      (or (and (zero? px) (zero? qy) (> qx px))
          (and (zero? px) (= py qy) (> qx px))
          (and (zero? qy) (= px qx) (> py qy))))))

(def upper 3)

(def right-angles 
  (for [px (range upper) py (range upper)
        qx (range upper) qy (range upper)
        :let [p [px py] q [qx qy]]
        :when (right-triangle? p q)]
    [p q]))

(println right-angles)
(println (count right-angles))

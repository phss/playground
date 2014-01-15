(defn right-triangle? [px py qx qy]
  (let [not-origin? (fn [x y] (not= [0 0] [x y]))]
    (and (not-origin? px py) (not-origin? qx qy)
      (or (and (zero? px) (zero? qy) (> qx px))
          (and (zero? px) (= py qy) (> qx px))
          (and (zero? qy) (= px qx) (> py qy))))))

(def upper 3)

(def right-angles 
  (for [px (range upper)
        py (range upper)
        qx (range upper)
        qy (range upper)
        :when (right-triangle? px py qx qy)]
    [[px py] [qx qy]]))

(println right-angles)
(println (count right-angles))

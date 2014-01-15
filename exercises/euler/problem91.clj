(defn right-triangle? [px py qx qy]
  (or (and (zero? px) (zero? qy) (> qx px))
      (and (zero? px) (= py qy) (> qx px))
      (and (zero? qy) (= px qx) (> py qy))))

(def upper 3)

(def points (for [px (range upper)
                  py (range upper)
                  qx (range upper)
                  qy (range upper)
                  :when (and (not= [0 0] [px py])
                             (not= [0 0] [qx qy])
                             (right-triangle? px py qx qy))]
              [[px py] [qx qy]]))

(println points)
(println (count points))

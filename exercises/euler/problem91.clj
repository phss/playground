(defn right-triangle? [px py qx qy]
  (< px qx))

(def points (for [px (range 51)
                  py (range 51)
                  qx (range 51)
                  qy (range 51)
                  :when (right-triangle? px py qx qy)]
              true))

(println (count points))

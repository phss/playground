(ns queens)

(defn valid? [pos]
  (distinct? pos))

(valid? [0 0])

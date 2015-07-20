(ns queens)

(defn valid? [board]
  (apply distinct? board))

(valid? [0 0]) ;=> false
(valid? [0 1]) ;=> true



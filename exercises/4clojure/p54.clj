(ns fourclojure.p54)

(defn part [size s]
  (loop [col s res []]
    (if (< (count col) size)
      res
      (recur (drop size col) (conj res (take size col))))))

(part 3 (range 8))
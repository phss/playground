(ns day1.calc)

(defn increase?
  [[a b]]
  (> b a))


(defn solve
  [input]
  (->> input
       (partition 2 1)
       (filter increase?)
       (count)))
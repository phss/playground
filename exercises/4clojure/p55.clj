(ns fourclojure.p55)

(defn counto [s]
  (let [g (group-by identity s)]
    (reduce (fn [acc k] (update-in acc [k] count)) g (keys g))))

(counto [1 1 2 3 2 1 1])
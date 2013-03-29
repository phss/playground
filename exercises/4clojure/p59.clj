(ns fourclojure.p59)

(defn jus [& fs]
  (fn [& params]
    (map #(apply % params) fs)))

((jus + max min) 2 3 5 1 6 4)

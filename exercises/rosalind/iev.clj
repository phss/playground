
(defn pheno-prob [couples]
  (let [probs [1 1 1 0.75 0.5 0]]
    (apply + (map (fn [c p] (* c p 2)) couples probs))))


(def couples [17322 16926 16265 16610 18884 16862])


(time (println (pheno-prob couples)))

(ns fourclojure.p58)

(defn compo [& fs]
  (fn [& params]
    (let [funs (reverse fs)
          res (apply (first funs) params)]
      (loop [fns (rest funs) r res]
        (if (seq fns)
          (recur (rest fns) ((first fns) r))
          r)))))

((compo rest reverse) [1 2 3 4])

;(f1 (apply f2 params))
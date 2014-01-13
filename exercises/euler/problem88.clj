(use 'commons)
(use 'clojure.test)

(def k 12)
(def upper (* k 2))

(defn factors [up]
  (for [y (iterate inc 2)
        :let [f (* 2 y)]
        :while (<= f up)]
    [2 y]))

(println (factors upper))

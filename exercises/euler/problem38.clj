(use 'commons)

(defn pan-multiple? [n multipliers]
  (let [multiples (map (partial * n) multipliers)]
    (pandigital-1-9? (reduce concat (map digits-from multiples)))))

(println (pan-multiple? 192 [1 2 3]))
(println (pan-multiple? 9 [1 2 3]))

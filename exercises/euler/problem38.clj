(use 'commons)

(defn multicat [n multipliers]
  (number-from (mapcat (fn [m] (digits-from (* n m))) multipliers)))

(defn pan-multiple? [n multipliers]
  (pandigital-1-9-num? (multicat n multipliers)))

;(defn pan-multiple? [n]
;  (loop [multipliers [1 2]]))

(println (pan-multiple? 192 [1 2 3]))
(println (pan-multiple? 9 [1 2]))

(println (multicat 192 [1 2 3]))

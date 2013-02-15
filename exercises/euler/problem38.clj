(use 'commons)

(defn multicat [n multipliers]
  (long-from (mapcat (fn [m] (digits-from (* n m))) multipliers)))

(defn pan-multiple? [n]
  (loop [multipliers [1 2]]
    (let [possible-pan (multicat n multipliers)]
      (cond
        (pandigital-1-9-num? possible-pan) true
        (> possible-pan 987654321) false
        :else (recur (conj multipliers (inc (last multipliers))))))))

(println (pan-multiple? 192))
(println (pan-multiple? 8))

(println (multicat 192 [1 2 3]))

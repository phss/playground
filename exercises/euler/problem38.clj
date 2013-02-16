(use 'commons)

(defn multicat [n multipliers]
  (long-from (mapcat (fn [m] (digits-from (* n m))) multipliers)))

(defn pan-multiple [n]
  (loop [multipliers [1 2]]
    (let [possible-pan (multicat n multipliers)]
      (cond
        (pandigital-1-9-num? possible-pan) possible-pan
        (> possible-pan 987654321) nil
        :else (recur (conj multipliers (inc (last multipliers))))))))

(def all-pans (sort (remove nil? (map pan-multiple (range 1 10000000)))))

(println (last all-pans))

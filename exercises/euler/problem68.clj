(use 'commons)
(use 'clojure.math.combinatorics)

(def numbers (range 1 11))

(def ring-numbers (->>
                    (combinations numbers 5)
                    (mapcat (comp reverse permutations))))

(defn groups-for [ring]
  (partition 2 1 (conj ring (first ring))))

(defn gon-ring [ring]
  (let [groups (groups-for ring)
        seed (first (remove (set ring) numbers))
        first-set (concat [seed] (first groups))
        sum (reduce + first-set)]
    (loop [sets first-set remaining (rest groups)]
      (if (empty? remaining)
        sets
        (let [g (first remaining)
              e (- sum (reduce + g))]
          (if (or (> e (last numbers)) (some #{e} sets))
            nil
            (recur (concat sets (concat [e] g)) (rest remaining))))))))

;(println (gon-ring [3 2 1]))
;(println (gon-ring [3 6 1]))
(println (apply str (first (remove nil? (map gon-ring ring-numbers)))))

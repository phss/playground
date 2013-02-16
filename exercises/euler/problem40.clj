; Brute force
(def brute-champ (apply str (range 1000000)))
(def nths (take 7 (iterate (partial * 10) 1)))

(println (reduce * (map (fn [n] (Integer/parseInt (str (nth brute-champ n)))) nths)))




(def brute-champ (apply str (range 1000000)))
(def nths [1 10 100 1000 10000 100000 1000000])

(println (count brute-champ))

(println (reduce * (map (fn [n] (Integer/parseInt (str (nth brute-champ n)))) nths)))

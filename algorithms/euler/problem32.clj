
(defn nine-pan? [n]
  (let [digits (map #(Integer/parseInt (str %)) (str n))]
    (= (range 1 10) (sort digits))))


(println (nine-pan? 234568972)) ; should be false
(println (nine-pan? 1234568971)) ; should be false
(println (nine-pan? 234568971)) ; should be true

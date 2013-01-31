
(defn pow [n e]
  (int (Math/pow n e)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

(println (for [n (range 2 9999)
               :let [sum (reduce + (map #(pow % 4) (digits n)))]
               :when (= n sum)]
           n))

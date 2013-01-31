
(defn pow [n e]
  (int (Math/pow n e)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

(println (for [n (range 2 999999)
               :let [sum (reduce + (map #(pow % 5) (digits n)))]
               :when (= n sum)]
           n))

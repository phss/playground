
(defn pow [n e]
  (int (Math/pow n e)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

(println (for [a (range 10)
               b (range 10)
               :let [sum (+ (pow a 2) (pow b 2))]
               :when (= [a b] (digits sum))]
           [[a b] (digits sum)]))

(defn factorial [num]
  (apply * (range 1 (inc num))))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn curious-fact? [n]
  (let [facts (map factorial (digits n))]
    (= n (reduce + facts))))

(println (curious-fact? 145))
(println (curious-fact? 120))

(println (reduce + (filter curious-fact? (range 1 1000000))))

(use 'commons)
(use 'clojure.test)

(defn converge [fracs]
  (let [as (reverse fracs)]
    (loop [c (first as) remas (rest as)]
      (let [a (first remas)]
        (if (nil? a)
          c
          (recur (+ a (/ 1 c)) (rest remas)))))))

(is (= (/ 3 2) (converge [1 2])))
(is (= (/ 41 29) (converge [1 2 2 2 2])))

(is (= (/ 1457 536) (converge [2 1 2 1 1 4 1 1 6 1])))

(def euler-continued-fraction (concat [2 1] (mapcat (fn [n] [n 1 1]) (iterate (partial + 2) 2))))

(println (->> 
           euler-continued-fraction
           (take 100)
           (converge)
           (numerator)
           (digits-from)
           (reduce +)))

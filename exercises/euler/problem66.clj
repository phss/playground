(use 'commons)
(use 'clojure.test)

(defn perfect-square? [n]
  (let [s (Math/sqrt n)]
    (no-decimal? s)))

(defn solve-dio [d]
  (if (perfect-square? d)
    nil
    (first (for [x (iterate inc 2)
                 y (range 1 (inc (/ x 2))) 
                 :when (= 1 (- (pow2 x) (* d (pow2 y))))]
             [x y]))))

(is (nil? (solve-dio 4)))
(is (= [9 4] (solve-dio 5)))

(println (map (comp first solve-dio) [2 3 5 6 7]))

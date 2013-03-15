(use 'commons)
(use 'clojure.test)

(defn perfect-square? [n]
  (let [s (Math/sqrt n)]
    (no-decimal? s)))

(defn solve-dio [d]
  (if (perfect-square? d)
    nil
    (first (for [y (iterate inc 2)
                 :let [x (sqrt (inc (* d (pow2 y))))] 
                 :when (no-decimal? x)]
             [(int x) y]))))

(is (nil? (solve-dio 4)))
(is (= [9 4] (solve-dio 5)))

(println (map (comp first solve-dio) [2 3 5 6 7]))

;(doseq [i (range 2 1001)]
;  (println i (solve-dio i)))

(println (->>
           (range 2 1001)
           (map (comp first solve-dio))
           (sort)
           (last)))

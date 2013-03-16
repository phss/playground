(use 'commons)
(use 'clojure.test)

(defn numer [n]
  (if (ratio? n) (numerator n) n))

(defn denom [n]
  (if (ratio? n) (denominator n) 1))

(defn pell-equation [x y d]
  (- (* x x) (* d y y)))

(defn solve-pell? [x y d]
  (= 1 (pell-equation x y d)))

(defn solve-dio [d]
  (if (perfect-square? d)
    nil
    (first (for [c (all-convergents (root-continued-fractions d))
                 :let [x (numer c)
                       y (denom c)] 
                 :when (solve-pell? x y d)]
             [x y]))))

(is (nil? (solve-dio 4)))
(is (= [9 4] (solve-dio 5)))

(println (map (comp first solve-dio) [2 3 5 6 7]))

(println (->>
           (range 2 1001)
           (map (fn [d] 
                  (let [[x y] (solve-dio d)]
                    [d x y])))
           (sort-by second)
           (last)))

(use 'commons)
(use 'clojure.test)

(defn numer [n]
  (if (ratio? n)
    (numerator n)
    n))

(defn denom [n]
  (if (ratio? n)
    (denominator n)
    1))

(defn solve-pell? [x y d]
  (= 1 (- (pow2 x) (* d (pow2 y)))))

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

;(doseq [i (range 900 911)]
;  (println i (solve-dio i)))

; 900 nil
; 901 [1801 60]
; 902 [901 30]
; 903 [601 20]
; 904 [451 15]
; 905 [361 12]
; 906 [301 10]
; 907 [1028830619 34161760]
; 908 [102151 3390]
; 909 [80801 2680]
; 910 [181 6]


(println (->>
           (range 2 1001)
           (map (comp first solve-dio))
           (sort)
           (last)))

;(doseq [p (partition-all 50 x-col)]
;  (println (last (sort p))))

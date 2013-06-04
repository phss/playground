(use 'commons)

;(def natural-numbers (range 1 30))
;(println
; (distinct (for [a natural-numbers
;       b natural-numbers
;       c natural-numbers
;       d natural-numbers
;       :let [nums [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 a b c d]]
;       :when (= (reduce + nums) (reduce * nums))]
;   (sort nums))))

(defn increment [path]
  (for [i (range (count path))]
    (conj (update-in path [i] inc) 1)))


(defn increment-all [paths]
  (set (mapcat increment paths)))

(println (increment-all [[2]]))
(println (increment-all [[3 1] [2 2]]))
;(println (increment-all (increment-all [[3 1] [2 2]])))


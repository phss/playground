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
  (let [next-level-path (conj path 1)] 
    (for [i (range (count next-level-path))
          :let [new-path (update-in next-level-path [i] inc)]
          :when (= new-path (sort-by #(* % -1) new-path))]
      new-path)))


(defn increment-all [paths]
  (set (mapcat increment paths)))

(println (increment-all [[2]]))
(println (increment-all [[3 1] [2 2]]))
(println (increment-all (increment-all [[3 1] [2 2]])))


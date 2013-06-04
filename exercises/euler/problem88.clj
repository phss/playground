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
  (let [base-path (conj path 1)] 
    (for [i (range (count base-path))
          :let [new-path (update-in base-path [i] inc)]
          :when (= new-path (sort-by #(* % -1) new-path))]
      new-path)))

(defn next-level [level]
  (set (mapcat increment level)))

(defn paths
  ([] (paths [[2]]))
  ([level] (concat level (lazy-seq (paths (next-level level)))) ))

(defn equal-sum-prod? [path k]
  (let [sum (reduce + (conj path (- k (count path))))
        prod (reduce * path)]
    (= sum prod)))


(println (first (filter #(equal-sum-prod? % 8000) (paths))))

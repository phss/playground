(use 'commons)

(def max-n 101)

(defn totient [n]
  (let [f (distinct (prime-factors n))]
    (int (reduce * (conj (map #(- 1 (/ 1 %)) f) n)))))

(doseq [n (range 2 max-n)]
  (println n (totient n)))

;2 1
;3 2
;4 2
;5 4
;6 2
;7 6
;8 4
;9 6
;10 4

(def totients (map (fn [n]
                     (let [t (totient n)]
                       [n t (/ n t)])) 
                   (range 2 max-n)))

(time (println (last (sort-by last totients))))

(use 'commons)

(defn multi-same-digits? [n]
  (let [multis (map (partial * n) (range 1 3))
        multi-digits (map #(sort (digits-from %)) multis)]
    (apply = multi-digits)))

(time (println (first (filter multi-same-digits? (iterate inc 1)))))

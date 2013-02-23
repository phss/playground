(use 'commons)

(defn multi-same-digits? [n]
  (let [digits-n (digits-from n)
        digits-2n (digits-from (* n 2))]
    (= (sort digits-n) (sort digits-2n))))

(time (println (first (filter multi-same-digits? (iterate inc 1)))))

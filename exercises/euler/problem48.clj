(use 'commons)

(defn last-10-digits [n]
  (let [d (digits-from n)]
    (long-from (take-last 10 d))))

(defn pow [n e]
  (loop [i e p n]
    (if (= 1 i)
      p
      (recur (dec i) (last-10-digits (* p n))))))


(println (last-10-digits (reduce + (map #(pow % %) (range 1 1001)))))

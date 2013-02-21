(use 'commons)

(defn last-10-digits [n]
  (let [d (digits-from n)]
    (number-from (take-last 10 d))))

(defn pow [n e]
  (loop [i e p n]
    (if (= 1 i)
      p
      (recur (dec i) (* p n)))))


(println (last-10-digits (reduce + (map #(pow % %) (range 1 11)))))

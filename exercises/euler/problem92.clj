(use 'commons)

(defn next-square-chain [number]
  (let [d (digits-from number)]
    (reduce + (map pow2 d))))

(defn arrives [number]
  (loop [n number]
    (if (or (= n 1) (= n 89))
      n
      (recur (next-square-chain n)))))

(println (arrives 44))
(println (arrives 85))

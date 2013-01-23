
(defn next-n [n]
  (if (even? n)
    (/ n 2)
    (+ 1 (* 3 n))))

(defn collatz-seq [n]
  (loop [s [n]]
    (let [l (last s)] 
      (if (= 1 l)
        s
        (recur (conj s (next-n l)))))))

(defn longest-collatz-under [n]
  (let [all-lengths (map-indexed vector (map (comp count collatz-seq) (range 1 n)))]
    (last (sort-by second all-lengths))))

(time (println (longest-collatz-under 100)))

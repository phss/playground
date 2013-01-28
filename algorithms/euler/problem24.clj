(defn factorial [n]
  (if (= 1 n)
    1
    (* n (factorial (dec n)))))

(defn nth-permutation [elems nt]
  (loop [n (dec nt) available (range elems) perm []]
    (let [group-size (/ (factorial (count available)) (count available))
          idx (quot n group-size)
          next-n (rem n group-size)]
      (if (= 1 (count available))
        (concat perm available)
        (recur next-n (concat (take idx available) (drop (inc idx) available)) (conj perm (nth available idx)))))))

(time (println (nth-permutation 10 1000000)))

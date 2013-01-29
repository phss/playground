(defn factorial [n]
  (if (= 1 n) 1 (* n (factorial (dec n)))))

(defn remove-idx [col idx]
  (concat (take idx col) (drop (inc idx) col)))

(defn nth-permutation [elems nt]
  (loop [n (dec nt) available (range elems) perm []]
    (let [avails (count available) 
          group-size (/ (factorial avails) avails)
          idx (quot n group-size)
          next-n (rem n group-size)]
      (if (= 1 (count available))
        (concat perm available)
        (recur next-n (remove-idx available idx) (conj perm (nth available idx)))))))

(time (println (nth-permutation 10 1000000)))

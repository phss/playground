(defn factorial [n]
  (if (= 1 n) 1 (* n (factorial (dec n)))))

(defn remove-idx [col idx]
  (concat (take idx col) (drop (inc idx) col)))

(defn nth-permutation [elems nt]
  (loop [n (dec nt) available elems perm []]
    (let [avails (count available) 
          group-size (/ (factorial avails) avails)
          idx (quot n group-size)
          next-n (rem n group-size)]
      (if (= 1 (count available))
        (concat perm available)
        (recur next-n (remove-idx available idx) (conj perm (nth available idx)))))))

(def all-permutations (map (fn [n] (nth-permutation (range 1 10) n)) (range 1 (inc (factorial 9)))))

;(time (println (last all-permutations)))
;

(defn sqrt [n]
  (int (Math/sqrt n)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn nine-pan? [d]
  (= (range 1 10) (sort d)))


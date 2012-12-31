
(defn permutations [n]
  (let [e (range 1 (inc n))
        expand-perms (fn [new-perms perm]
                       (concat new-perms (for [x e :when (not (some #{x} perm))]
                                           (conj perm x))))]
    (loop [perms (map vector e) c 1]
      (if (= c n)
        perms
        (recur (reduce expand-perms [] perms) (inc c))))))


(def perms (permutations 5))

(println (count perms))
(doseq [perm perms]
  (println (clojure.string/join " " perm)))

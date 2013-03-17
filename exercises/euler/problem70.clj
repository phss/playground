(use 'commons)

(defn totient [n]
  (let [f (distinct (prime-factors n))]
    (int (reduce * (conj (map #(- 1 (/ 1 %)) f) n)))))

(defn totient-perm? [n]
  (let [tot (totient n)]
    (= (sort (digits-from n)) (sort (digits-from tot)))))

(def tot-perms (map (fn [n]
                      (let [t (totient n)]
                        [n t (double (/ n t)) (prime-factors n)])) 
                    (filter totient-perm? (range 2 10001))))

(println (count tot-perms))

(doseq [t (reverse (sort-by #(nth % 2) tot-perms))]
  (println t))

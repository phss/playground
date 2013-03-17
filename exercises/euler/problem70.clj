(use 'commons)

(defn totient [n]
  (let [f (distinct (prime-factors n))]
    (int (reduce * (conj (map #(- 1 (/ 1 %)) f) n)))))

(defn totient-perm? [n]
  (let [tot (totient n)]
    (= (sort (digits-from n)) (sort (digits-from tot)))))

(println (totient-perm? 87109))

(def tot-perms (filter totient-perm? (range 2 10001)))

(println (count tot-perms))
(doseq [tp tot-perms]
  (println tp (prime-factors tp)))

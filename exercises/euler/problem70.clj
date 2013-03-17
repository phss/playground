(use 'commons)
(use 'clojure.math.combinatorics)

(defn prod-seq [n f]
  (int (reduce * (conj (map #(- 1 (/ 1 %)) f) n))))

(defn perm? [a b]
  (= (sort (digits-from a)) (sort (digits-from b))))

(def primes (primes-up-to 5000))

(def composites (map (fn [f] [(reduce * f) f]) (combinations primes 2)))

(def tots (->> 
            composites
            (map (fn [[n f]]
                   (let [t (prod-seq n f)]
                     [n t (double (/ n t))])))
            (filter (fn [[n t r]] (perm? n t)))))

(doseq [t (reverse (sort-by last tots))]
  (println t))

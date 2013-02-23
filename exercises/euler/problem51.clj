(use 'commons)

(def primes (take 10000 all-primes))

(defn star-digits-from [n indexes]
  (let [d (vec (digits-from n))
        in-bound-indexes (filter #(< % (count d)) indexes)
        starred-digits (reduce (fn [sd i] (assoc-in sd [i] "*")) d in-bound-indexes)]
    (apply str starred-digits)))

(defn matching-digits? [n indexes]
  (let [d (digits-from n)
        in-bound-indexes (filter #(< % (count d)) indexes)
        idx-digits (map #(nth d %) in-bound-indexes)]
    (if (= 0 (count in-bound-indexes))
      false
      (reduce = idx-digits))))

(def groups (->> 
              primes
              (group-by (fn [p] (star-digits-from p [1])))
              (map second)
              (map (fn [group] (filter (fn [n] (matching-digits? n [1])) group)))
              (remove (fn [group] (< (count group) 7)))))

(println groups)

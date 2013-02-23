(use 'commons)

(def primes (primes-up-to 1000000))

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

(defn digit-replacement-groups [indexes min-family-size] 
  (println indexes)
  (->> primes
       (group-by (fn [p] (star-digits-from p indexes)))
       (map second)
       (map (fn [group] (filter (fn [n] (matching-digits? n indexes)) group)))
       (remove (fn [group] (< (count group) min-family-size)))))

(def max-index (count (digits-from (last primes))))

(def all-indexes (distinct (map (comp sort distinct digits-from) (range))))

(time (println (first (for [indexes (take-while #(< (count %) max-index) all-indexes)
                     :let [groups (digit-replacement-groups indexes 7)]
                     :when (not-empty groups)]
                  groups))))

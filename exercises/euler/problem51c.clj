(use 'commons)

(def primes (vec (filter prime? (range 100000 1000000))))

(println "Generated primes")

(defn star-digits-from [n indexes]
  (let [d (vec (digits-from n))
        starred-digits (reduce (fn [sd i] (assoc-in sd [i] "*")) d indexes)]
    (apply str starred-digits)))

(defn matching-digits? [n indexes]
  (let [d (vec (digits-from n))
        idx-digits (map #(nth d %) indexes)]
    (apply = idx-digits)))

(defn digit-replacement-groups [indexes min-family-size] 
  (->> primes
       (reduce (fn [groups prime] 
                 (if (matching-digits? prime indexes)
                   (update-in groups [(star-digits-from prime indexes)] conj prime)
                   groups)) 
               {})
       (remove (fn [[mask group]] (< (count group) min-family-size)))))

(def max-index (count (digits-from (last primes))))

(def all-indexes (->> (range)
                      (map (comp sort distinct digits-from))
                      (remove (fn [digits] (some #(<= max-index %) digits)))
                      (distinct)
                      (take-while #(< (count %) max-index))))

(time (println (first (for [indexes (take-while #(< (count %) max-index) all-indexes)
                     :let [groups (digit-replacement-groups indexes 8)]
                     :when (not-empty groups)]
                  groups))))

(use 'commons)

;(def primes (vec (primes-up-to 1000000)))
;(def primes (vec (drop-while #(<= % 10000) (take-while #(<= % 99999) all-primes))))
;(def primes (vec (drop-while #(<= % 100000) (take-while #(<= % 999999) all-primes))))

(def primes (vec (filter prime? (range 10000000 100000000))))

(println "Generated primes")

(defn star-digits-from [n indexes]
  (let [d (vec (digits-from n))
        starred-digits (reduce (fn [sd i] (assoc-in sd [i] "*")) d indexes)]
    (apply str starred-digits)))

(defn matching-digits? [n indexes]
  (let [d (vec (digits-from n))
        idx-digits (map #(nth d %) indexes)]
    (reduce = idx-digits)))

(defn digit-replacement-groups [indexes min-family-size] 
  (println indexes)
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

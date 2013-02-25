(use 'commons)
(use 'clojure.test)
(use 'clojure.set)

(def max-index 10000)

(def primes (vec (take max-index all-primes)))

(println "Generated primes")

(def not-nil? (complement nil?))

(defn mask-from [num-a num-b]
  (loop [mask [] masked-a nil masked-b nil da (digits-from num-a) db (digits-from num-b)]
    (let [a (first da) b (first db)]
      (cond
        (and (nil? a) (nil? b)) (apply str mask)
        (or (nil? a) (nil? b)) nil
        (= a b) (recur (conj mask a) masked-a masked-b (rest da) (rest db))
        (and (not-nil? masked-a)  (or (not= a masked-a) (not= b masked-b))) nil
        :else (recur (conj mask "*") a b (rest da) (rest db))))))
 

(is (= "1234" (mask-from 1234 1234)))
(is (= "1*34" (mask-from 1234 1334)))
(is (= "1**4" (mask-from 1224 1334)))
(is (= "****" (mask-from 2222 3333)))
(is (= nil (mask-from 123 12)))
(is (= nil (mask-from 1234 1344)))

(println (last primes))

(def groups-for (for [i (range (dec max-index))
                  j (range (inc i) max-index)
                  :let [pi (nth primes i)
                        pj (nth primes j)
                        mask (mask-from pi pj)]
                  :when (not-nil? mask)]
              [mask pi pj]))

(def groups (reduce (fn [g [m i j]]
                      (update-in g [m] union #{i j})) 
                    {} groups-for))

(println (filter (fn [[k v]] (<= 7 (count v))) groups))

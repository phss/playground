; Prime checking and generation
(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (not-any? (partial divisible? n) (range 2 (inc (int (Math/sqrt n)))))))

(defn primes-up-to [n]
  (filter prime? (range 2 n)) )


; Number wrangling
(defn digits-from [number]
  (map #(Integer/parseInt (str %)) (str number)) )

(defn number-from [digits]
  (Integer/parseInt (apply str digits)))

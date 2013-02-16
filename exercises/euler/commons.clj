(ns commons)

; Math funcs
(defn sqrt [n]
  (Math/sqrt n))

(defn int-sqrt [n]
  (int (sqrt n)))

(defn pow2 [n]
  (* n n))

(defn no-decimal? [n]
  (= (mod n 1) 0.0))

(defn pyth [a b]
  (sqrt (+ (pow2 a) (pow2 b))))


; Prime checking and generation
(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (and (> n 1)
         (not-any? (partial divisible? n) (range 2 (inc (int-sqrt n)))))))

(defn primes-up-to [n]
  (filter prime? (range 2 n)) )


; Number wrangling
(defn digits-from [number]
  (map #(Integer/parseInt (str %)) (str number)) )

(defn number-from [digits]
  (Integer/parseInt (apply str digits)))

(defn long-from [digits]
  (Long/parseLong (apply str digits)))


; Pandigitals
(defn pandigital? [digits]
  (= (range 1 (inc (count digits))) (sort digits)))

(defn pandigital-num? [number]
  (pandigital? (digits-from number)))

(defn pandigital-1-9? [digits]
  (pandigital? digits))

(defn pandigital-1-9-num? [number]
  (pandigital-1-9? (digits-from number)))

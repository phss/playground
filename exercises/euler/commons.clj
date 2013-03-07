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

(defn divisible? [n d] 
  (zero? (rem n d)))

(defn factorial [num]
  (apply * (range (bigint 1) (inc (bigint num)))))

; Prime checking and generation
(defn prime? [n]
  (let [divisible? (fn [n d] (zero? (rem n d)))]
    (and (> n 1)
         (not-any? (partial divisible? n) (range 2 (inc (int-sqrt n)))))))

(defn primes-up-to [n]
  (filter prime? (range 2 n)))

(def all-primes (filter prime? (iterate inc 2)))

(defn first-factor [num]
  (if (= 1 num)
    nil
    (first (filter (partial divisible? num) (iterate inc 2)))))

(defn prime-factors [num]
  (loop [n num factors []]
    (if (= n 1)
      factors
      (let [f (first-factor n)]
        (recur (quot n f) (conj factors f))))))


; Number wrangling
(defn digits-from [number]
  (map #(Integer/parseInt (str %)) (str number)) )

(defn number-from [digits]
  (Integer/parseInt (apply str digits)))

(defn long-from [digits]
  (Long/parseLong (apply str digits)))

(defn bigint-from [digits]
  (bigint (apply str digits)))


; Pandigitals
(defn pandigital? [digits]
  (= (range 1 (inc (count digits))) (sort digits)))

(defn pandigital-num? [number]
  (pandigital? (digits-from number)))

(defn pandigital-1-9? [digits]
  (pandigital? digits))

(defn pandigital-1-9-num? [number]
  (pandigital-1-9? (digits-from number)))

; Palindromes

(defn palindrome? [number]
  (let [s (str number)
        middle (int (/ (count s) 2))
        left (subs s 0 middle)
        right (apply str (reverse (subs s (if (even? (count s)) middle (inc middle)))))]
    (= left right)))

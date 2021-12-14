(ns day3.part1
  (:require
   [parser :as parser]))

(defn parse-line
  [line]
  (chars (char-array line)))

(def sorted-bit-freqs
  (->> "data/day3.txt"
       (parser/input parse-line)
       (apply map vector)
       (map frequencies)
       (map #(sort-by val > %))))

(defn rate
  [pickFn freqs]
  (letfn [(toDecimal [s]
            (Integer/parseInt s 2))]
    (->> freqs
         (map pickFn)
         (map key)
         (apply str)
         (toDecimal))))

(def gamma-rate
  (rate first sorted-bit-freqs))

(def epsilon-rate
  (rate last sorted-bit-freqs))

(println (* gamma-rate epsilon-rate))

(ns day3.part1
  (:require
   [parser :as parser]))

(defn parse-line
  [line]
  (chars (char-array line)))

(def bits
  (->> "data/day3.txt"
       (parser/input parse-line)
       (map #(apply vector %))))

(defn pick-criteria-bit
  [pickFn bits]
  (let [bit-freqs (frequencies bits)
        sortfn (if (apply = (vals bit-freqs))
                 #(int (key %))
                 val)]
    (->> bit-freqs
         (sort-by sortfn >)
         (pickFn)
         (key))))

(defn rate
  ([pickFn] (rate pickFn 0 bits))
  ([pickFn n remaining-bits]
   (if (= (count remaining-bits) 1)
     (Integer/parseInt (apply str (first remaining-bits)) 2)
     (let [criteria-bit (pick-criteria-bit pickFn (map #(nth % n) remaining-bits))
           next-bits (filter #(= criteria-bit (nth % n)) remaining-bits)]
       (recur pickFn (inc n) next-bits)))))

(def oxygen-rate
  (rate first))
(println oxygen-rate)

(def co2-rate
  (rate last))
(println co2-rate)

(println (* oxygen-rate co2-rate))

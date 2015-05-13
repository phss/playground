(ns problem97
  (:use [commons]))

(def exponent 7830457)

(defn last-10-digits [n]
  (->> (digits-from n)
       (take-last 10)
       (apply str)
       (read-string)))

;(last-10-digits 99999991234567890)

(defn next-doubled-10 [n]
  (last-10-digits (* n 2)))

;(next-doubled-10 123456789000)

(def doubles-seq (iterate next-doubled-10 1))

(nth doubles-seq 40)

(ns problem97
  (:use [commons]))

(def exponent 7830457)

(defn last-10-digits [n]
  (->> (digits-from n)
       (take-last 10)
       (apply str)
       (bigdec)))

;(last-10-digits 99999991234567890)

(defn next-doubled-10 [n]
  (last-10-digits (* n 2)))

;(next-doubled-10 123456789000)

(def doubles-seq (iterate next-doubled-10 1))

(def big-double (nth doubles-seq 7830457))

(->> big-double
     (* 28433)
     (inc)
     (last-10-digits))

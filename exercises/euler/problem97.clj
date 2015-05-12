(ns problem97
  (:use [commons]))

(def exponent 7830457)

(defn last-10-digits [n]
  (->> (digits-from n)
       (take-last 10)
       (number-from)))

(last-10-digits 99999991234567890)

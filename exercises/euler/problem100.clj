(ns problem100
  (:require  [clojure.math.numeric-tower :as math]))

(defn valid? [t b]
  (= 1/2 (* (/ b t) (/ (dec b) (dec t)))))

(defn always-false [t b] false)

;(valid? 120 85)

; Trying to find a possible solution through this formula
; sqrt(b^2 - b) = sqrt(t^2 - t / 2)
(defn possible-solution [t]
  (->>
    (/ (- (math/expt t 2) t) 2)
    (math/sqrt)
    (math/ceil)
    (bigint)))


(def square-of-2 (math/sqrt 2))


(defn possible-solution-from-b [b]
  (bigint (math/floor (* square-of-2 b))))

;(possible-solution 21)

(time (println (->>
  (range 10 10009)
  ;(range 1000000000000 2000000000000)
  ;[1000000000000]
  ;(iterate inc 1000000000000)
  ;(map (fn [b] [b b]))
  (map (fn [b] [(possible-solution-from-b b) b]))
  ;(map (fn [t] [t (possible-solution t)]))
  ;(filter #(apply always-false %))
  (filter #(apply valid? %))
  ;(map (fn [[a b]] [a b (double (/ a b))]))
  ;(first)
  
  )))

(ns problem100
  (:require  [clojure.math.numeric-tower :as math]))

(defn valid? [t b]
  (= 1/2 (* (/ b t) (/ (dec b) (dec t)))))

;(valid? 120 85)

; Trying to find a possible solution through this formula
; sqrt(b^2 - b) = sqrt(t^2 - t / 2)
(defn possible-solution [t]
  (->>
    (/ (- (math/expt t 2) t) 2)
    (math/sqrt)
    (math/ceil)
    (bigint)))

;(possible-solution 21)

(println (->>
  (range 10 3000000)
  ;[1000000000000]
  ;(iterate inc 1000000000000)
  (map (fn [t] [t (possible-solution t)]))
  (filter #(apply valid? %))
  ;(first)
  
  ))

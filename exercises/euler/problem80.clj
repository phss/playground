(use 'commons)

(defn digit-pair [n]
  (let [digits (digits-from n)
        padded-digits (if (odd? (count digits)) (cons 0 digits) digits)]
    (partition 2 padded-digits)))

(def integer-digit-pairs (map digit-pair (iterate inc 1)))

(defn determine-root-x-y [c p]
  (let [xs (reverse (range 10))
        ys (map (fn [x] (* x (+ x (* 20 p)))) xs)]
    (first (filter (fn [[x y]] (<= y c)) (map vector xs ys)))))

(defn root-digits [integer-digit-pair upto]
  (loop [digit-pairs (concat integer-digit-pair (repeat [0 0])) 
         r (bigint 0) p (bigint 0) 
         root-digits []]
    (let [c (+ (* 100 r) (number-from (first digit-pairs))) 
          [x y] (determine-root-x-y c p)]
      (if (or (zero? c) (= upto (count root-digits)))
        root-digits
        (recur (rest digit-pairs) (- c y) (+ x (* p 10)) (conj root-digits x))))))

(println (digit-pair 8))
(println (digit-pair 11))
(println (root-digits (digit-pair 11) 10))
;(println (apply + (root-digits (digit-pair 99) 100)))

;(println (root-digits (digit-pair 2) 100))
;(println (apply + (root-digits (digit-pair 2) 100)))

(time (println 
  (->>
    (range 1 101)
    (remove #(no-decimal? (sqrt %)))
    (map (fn [i] (apply + (root-digits (digit-pair i) 100))))
    (apply +))))

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
    (let [c (+ (* 100 r) (apply + (first digit-pairs))) 
          [x y] (determine-root-x-y c p)]
      (if (or (zero? c) (= upto (count root-digits)))
        root-digits
        (recur (rest digit-pairs) (- c y) (+ x (* p 10)) (conj root-digits x))))))

(println (apply + (root-digits (digit-pair 2) 100)))

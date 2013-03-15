(use 'commons)
(use 'clojure.test)

(defn sqrt-period [n]
  (let [a0 (int (Math/floor (Math/sqrt n)))
        delta (Math/sqrt (* 4 n))]
    (loop [p [] a 1 b (* 2 a0) c (- (pow2 a0) n)]
      (let [d (int (/ (+ b delta) (* 2 c)))
            new-p (conj p (Math/abs d))]
        (if (= 1 (Math/abs c))
          new-p
          (recur new-p c (+ (- b) (* 2 c d)) (+ a (* b d -1) (* c (pow2 d)))))))))

(is (= [2] (sqrt-period 2)))
(is (= [1 1 1 4] (sqrt-period 7)))
(is (= [3 6] (sqrt-period 11)))
(is (= [] (sqrt-period 4)))

(use 'commons)
(use 'clojure.test)

(defn expanded-to [n]
  (loop [e 2 c n]
    (if (= c 1)
      (+ 1 (/ 1 e))
      (recur (+ 2 (/ 1 e)) (dec c)))))

(is (= (/ 3 2) (expanded-to 1)))
(is (= (/ 41 29) (expanded-to 4)))

(defn more-nums? [f]
  (let [num-digits (digits-from (numerator f))
        den-digits (digits-from (denominator f))]
    (> (count num-digits) (count den-digits))))

(println (count (filter more-nums? (map expanded-to (range 1 1001)))))

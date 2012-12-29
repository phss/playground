
(defn pals [n]
  (let [digits (str n)
        even (even? (count digits))
        mid-idx (quot (count digits) 2)
        middle (if even "" (get digits mid-idx))
        left-side (subs digits 0 mid-idx)
        right-side (subs digits (if even mid-idx (inc mid-idx)))
        next-pal (fn [n]
                (let [toi (fn [st] (Long. st))
                      x (if (<= (toi right-side) (toi (clojure.string/reverse left-side)))
                          (str left-side middle)
                          (str (inc (toi (str left-side middle)))))
                      r (clojure.string/reverse (if (= middle "")
                                    x
                                    (subs x 0 (dec (count x)))))]
                  (toi (str x r))))
        np (if (< n 10) n (next-pal n))] 
    (cons np (lazy-seq (pals (inc np))))))


(println (take 26  (pals 0)))

(println (take 16  (pals 162)))

(println (take 6  (pals 1234550000))) ; [1234554321 1234664321 1234774321 1234884321 1234994321 1235005321]

(println (nth (pals 0) 10101)) ; 9102019

(println (take 5 (pals 8800))) ; 9102019

(println (* 111111111 111111111))
(println (first (pals (* 111111111 111111111))))

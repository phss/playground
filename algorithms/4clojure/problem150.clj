(defn toi [s]
  (Integer. s))

(defn splice [n]
  (let [s (str n)
        mid (quot (count s) 2)
        pivot (if (even? (count s)) "" (get s mid))
        left (subs s 0 mid)
        right (subs s (if (even? (count s)) mid (inc mid)))]
    [left pivot right]))

(defn nextp [n]
  (let [[l p r] (splice n)
        x (if (< (toi r) (toi (clojure.string/reverse l)))
              (str l p)
              (str (inc (toi (str l p)))))
        r (clojure.string/reverse (if (= p "")
                                    x
                                    (subs x 0 (dec (count x)))))]
     (toi (str x r))))

(defn pals [n]
  (let [np (if (< n 10) n (nextp n))] 
    (cons np (lazy-seq (pals (inc np))))))


(println (take 26  (pals 0)))

(println (take 16  (pals 162)))

(println (take 6  (pals 1234550000))) ; [1234554321 1234664321 1234774321 1234884321 1234994321 1235005321]

(println (nth (pals 0) 10101)) ; 9102019

(println (take 5 (pals 8800))) ; 9102019

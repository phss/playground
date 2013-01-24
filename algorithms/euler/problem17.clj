
(def to-str {
  0 "", 1 "one", 2 "two", 3 "three", 4 "four", 5 "five", 6 "six", 7 "seven", 8 "eight", 9 "nine", 10 "ten",
  11 "eleven", 12 "twelve", 13 "thirteen" 14 "fourteen", 15 "fifteen", 16 "sixteen", 17 "seventeen", 18 "eighteen", 19 "nineteen",
  20 "twenty", 30 "thirty", 40 "forty", 50 "fifty", 60 "sixty", 70 "seventy", 80 "eighty", 90 "ninety", 1000 "onethousand"
})

(defn digits [number]
  (loop [d [] n number]
    (if (zero? n)
      (reverse d)
      (recur (conj d (rem n 10)) (quot n 10)))))

(defn digit-to-number [digits]
  (Integer/parseInt (apply str digits)))

(defn num-to-str [number]
  (loop [d (digits number) s ""]
    (let [n (digit-to-number d)] 
      (cond (contains? to-str n) (str s (to-str n))
            (> n 99) (recur (rest d) (str s (to-str (first d)) (if (zero? (digit-to-number (rest d))) "hundred" "hundredand")))
            :else (recur (rest d) (str s (to-str (* 10 (first d)))))))))

(defn num-letter-count [nums]
  (count (mapcat num-to-str nums)))

(time (println (num-letter-count (range 1 1001))))

;(println (num-letter-count [342]))
;(println (num-letter-count [115]))
;(println (num-letter-count [100]))

;(doseq [n (range 1 102)]
;  (println n (num-to-str n) ))
(println (num-to-str 500))

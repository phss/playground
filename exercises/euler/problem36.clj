(defn gen-palindrome [num]
  (let [s (str num)
        i (apply str (reverse s))]
    (map read-string (cons (str s i) (map #(str s % i) (range 10))))))

; kind of crappy way of generating palindromes below a million
(def palindromes (sort (filter (partial > 1000000) (concat (range 1 10) (flatten (map gen-palindrome (range 1 1000)))))))

(defn to-bin [n]
  (Integer/toString n 2))

(defn pal? [s]
  (let [middle (int (/ (count s) 2))
        left (subs s 0 middle)
        right (apply str (reverse (subs s (if (even? (count s)) middle (inc middle)))))]
    (= left right)))

(def bin-pals (filter #(pal? (to-bin %)) palindromes))

(println (take 10 bin-pals))
(println (reduce + bin-pals))

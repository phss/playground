(defn gen-palindrome [num]
  (let [s (str num)
        i (apply str (reverse s))]
    (map read-string (cons (str s i) (map #(str s % i) (range 10))))))

; kind of crappy way of generating palindromes below a million
(def palindromes (sort (filter (partial > 1000000) (flatten (map gen-palindrome (range 1 1000))))))

(println (last palindromes))

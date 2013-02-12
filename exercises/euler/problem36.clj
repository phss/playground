(defn gen-palindrome [num]
  (let [s (str num)
        i (apply str (reverse s))]
    (map read-string (cons (str s i) (map #(str s % i) (range 10))))))

(def palindromes (sort (flatten (map gen-palindrome (range 1 100)))))

(println (last palindromes))

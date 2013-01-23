(ns eulerclj.problem4)

;A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 99.
;
;Find the largest palindrome made from the product of two 3-digit numbers.

(defn gen-palindrome [num]
  (let [s (str num)
        i (apply str (reverse s))]
    (map read-string (cons (str s i) (map #(str s % i) (range 10))))))

(defn factors-between? [num start end]
  (let [within-range? #(and 
                         (zero? (mod num %)) 
                         (< start (quot num %) end))]
    (some within-range? (range start end))))

(def palindromes (flatten (map gen-palindrome (range 100 1000))))

(apply max (filter #(factors-between? % 100 1000) palindromes))

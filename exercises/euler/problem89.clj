(use 'commons)

(def numbers
  (->>
    "files/problem89.txt"
    (slurp)
    (clojure.string/split-lines)))

(defn from-roman [roman]
  (let [replacements [[#"CM" "CCCCCCCCC"] [#"CD" "CCCC"]
                      [#"XC" "XXXXXXXXX"] [#"XL" "XXXX"]
                      [#"IX" "IIIIIIIII"] [#"IV" "IIII"]]
        numbers {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500 \M 1000} 
        simplify (reduce (fn [acc [pattern replacement]] (clojure.string/replace acc pattern replacement)) roman replacements)
        ]
    (reduce + (map numbers simplify))))

(defn to-roman [n]
  (loop [num n roman []]
    (cond
      (<= num 0) (apply str roman)
      (< num 4) (recur (- num 1) (conj roman "I"))
      (< num 5) (recur (- num 4) (conj roman "IV"))
      (< num 9) (recur (- num 5) (conj roman "V"))
      (< num 10) (recur (- num 9) (conj roman "IX"))
      (< num 40) (recur (- num 10) (conj roman "X"))
      (< num 50) (recur (- num 40) (conj roman "XL"))
      (< num 90) (recur (- num 50) (conj roman "L"))
      (< num 100) (recur (- num 90) (conj roman "XC"))
      (< num 400) (recur (- num 100) (conj roman "C"))      
      (< num 500) (recur (- num 400) (conj roman "CD"))
      (< num 900) (recur (- num 500) (conj roman "D"))
      (< num 1000) (recur (- num 900) (conj roman "CM"))
      (< num 10000) (recur (- num 1000) (conj roman "M"))
      :else "not there yet")))

(defn saved [roman-number]
  (let [number (from-roman roman-number) 
        minimal-roman-number (to-roman number)]
    (- (count roman-number) (count minimal-roman-number))))


(println (saved "VVIIIIII"))

(println (last numbers))

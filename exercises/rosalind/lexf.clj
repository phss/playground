
(defn lexo_strings [symbols n]
  (loop [strings symbols c 1]
    (if (= c n)
      strings
      (recur
        (reduce (fn [col string]
                  (concat col (map (partial str string) symbols))) 
                [] strings)
        (inc c)))))


(def syms ["T" "A" "G" "C"])
(def n 2)

(println (lexo_strings syms n))

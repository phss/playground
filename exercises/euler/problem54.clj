(use 'clojure.test)

(defn card [string]
  (let [values {\2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9, \T 10, \J 11, \Q 12, \K 13, \A 14 }]
    { :value (values (first string)), :suit (second string)}))

(defn parse-cards [string]
  (let [cards-string (clojure.string/split string #" ")]
    (map card cards-string)))

(defn winner [hands-string]
  (let [cards (parse-cards hands-string)
        p1-cards (take 5 cards)
        p2-cards (drop 5 cards)]
    p2-cards))

;(is (= :player2 (winner "5H 5C 6S 7S KD 2C 3S 8S 8D TD")))
;(is (= :player1 (winner "5D 8C 9S JS AC 2C 5C 7D 8S QH")))
;(is (= :player2 (winner "2D 9C AS AH AC 3D 6D 7D TD QD")))
;(is (= :player1 (winner "4D 6S 9H QH QC 3D 6D 7H QD QS")))
;(is (= :player1 (winner "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D")))

(println (winner "5H 5C 6S 7S KD 2C 3S 8S 8D TD"))

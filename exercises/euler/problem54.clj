(use 'clojure.test)

; Frequency 
(defn frequency? [values expected]
  (let [freq (map second (frequencies values))]
    (= (sort freq) (sort expected))))

(defn order-by-freq [values]
  (let [freq (frequencies values)]
    (reverse (map first (sort-by (fn [[v f]] (+ v (* 100 f))) freq)))))

; Card parsing
(defn card [string]
  (let [values {\2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9, \T 10, \J 11, \Q 12, \K 13, \A 14 }]
    { :value (values (first string)), :suit (second string)}))

(defn parse-cards [string]
  (let [cards-string (clojure.string/split string #" ")]
    (map card cards-string)))

; Ranking
(defn rank [r values]
  (let [ranks {:highest 0, :pair 1, :two-pairs 2}]
    (concat [(ranks r)] values)))

(defn highest-card [cards]
  (rank :highest (reverse (sort (map :value cards)))))
(is (= [0 6 5 5 3 2] (highest-card (parse-cards "2D 3D 5C 5D 6D"))))

(defn pair [cards]
  (let [values (map :value cards)]
    (if (frequency? values [2 1 1 1])
      (rank :pair (order-by-freq values))
      nil)))
(is (= nil (pair (parse-cards "2D 3D 4D 5D 6D"))))
(is (= [1 5 6 3 2] (pair (parse-cards "2D 3D 5C 5D 6D"))))

(defn two-pairs [cards]
  (let [values (map :value cards)]
    (if (frequency? values [2 2 1])
      (rank :two-pairs (order-by-freq values))
      nil)))
(is (= nil (two-pairs (parse-cards "2D 3D 5D 5D 6D"))))
(is (= [2 5 3 6] (two-pairs (parse-cards "3H 3D 5C 5D 6D"))))

(defn winner [hands-string]
  (let [cards (parse-cards hands-string)
        p1-cards (take 5 cards)
        p2-cards (drop 5 cards)]
    (highest-card p2-cards)))

;(is (= :player2 (winner "5H 5C 6S 7S KD 2C 3S 8S 8D TD")))
;(is (= :player1 (winner "5D 8C 9S JS AC 2C 5C 7D 8S QH")))
;(is (= :player2 (winner "2D 9C AS AH AC 3D 6D 7D TD QD")))
;(is (= :player1 (winner "4D 6S 9H QH QC 3D 6D 7H QD QS")))
;(is (= :player1 (winner "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D")))

;(println (winner "5H 5C 6S 7S KD 2C 3S 8S 8D TD"))

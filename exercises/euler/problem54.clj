(use 'clojure.test)

; Checks 
(defn frequency? [values expected]
  (let [freq (map second (frequencies values))]
    (= (sort freq) (sort expected))))

(defn order-by-freq [values]
  (let [freq (frequencies values)]
    (reverse (map first (sort-by (fn [[v f]] (+ v (* 100 f))) freq)))))

(defn consecutive-from-ace? [values])

(defn consecutive? [values]
  (let [sorted (sort values)]
    (and (apply distinct? sorted)
         (= (dec (count sorted)) (- (last sorted) (first sorted))))))

; Card parsing
(defn card [string]
  (let [values {\2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9, \T 10, \J 11, \Q 12, \K 13, \A 14 }]
    { :value (values (first string)), :suit (second string)}))

(defn parse-cards [string]
  (let [cards-string (clojure.string/split string #" ")]
    (map card cards-string)))

; Ranking
(defn rank [r values]
  (let [ranks [:highest :pair :two-pairs :three-of-a-kind :straight :flush :full-house :four-of-a-kind :straigh-flush :royal-flush]]
    (concat [(.indexOf ranks r)] values)))

(defn highest-rank [cards]
  (let [values (map :value cards)
        suits (map :suit cards)]
    (cond
      (and (consecutive? values) (frequency? suits [5]) (= 14 (last (sort values)))) :royal-flush
      (and (consecutive? values) (frequency? suits [5])) :straigh-flush
      (frequency? values [4 1]) :four-of-a-kind
      (frequency? values [3 2]) :full-house
      (frequency? suits [5]) :flush
      (consecutive? values) :straight
      (frequency? values [3 1 1]) :three-of-a-kind
      (frequency? values [2 2 1]) :two-pairs
      (frequency? values [2 1 1 1]) :pair
      :else :highest)))

(defn rank-cards [cards]
  (rank (highest-rank cards) (order-by-freq (map :value cards))))

(is (= [0 14 6 5 3 2] (rank-cards (parse-cards "2D 3D AC 5D 6D"))))
(is (= [1 5 6 3 2] (rank-cards (parse-cards "2D 3D 5C 5D 6D"))))
(is (= [2 5 3 6] (rank-cards (parse-cards "3H 3D 5C 5D 6D"))))
(is (= [3 3 6 5] (rank-cards (parse-cards "3H 3D 3C 5D 6D"))))
(is (= [4 6 5 4 3 2] (rank-cards (parse-cards "2D 3D 5C 4D 6D"))))
(is (= [4 5 4 3 2 1] (rank-cards (parse-cards "2D 3D 5C 4D AD"))))
(is (= [5 14 6 5 3 2] (rank-cards (parse-cards "2D 3D AD 5D 6D"))))
(is (= [6 3 5] (rank-cards (parse-cards "3H 3D 3C 5D 5H"))))
(is (= [7 3 5] (rank-cards (parse-cards "3H 3D 3C 3S 5H"))))
(is (= [8 13 12 11 10 9] (rank-cards (parse-cards "TD 9D KD QD JD"))))
(is (= [9 14 13 12 11 10] (rank-cards (parse-cards "AD KD QD JD TD"))))


(defn winner [hands-string]
  (let [cards (parse-cards hands-string)
        p1-cards (take 5 cards)
        p2-cards (drop 5 cards)]
    nil))

;(is (= :player2 (winner "5H 5C 6S 7S KD 2C 3S 8S 8D TD")))
;(is (= :player1 (winner "5D 8C 9S JS AC 2C 5C 7D 8S QH")))
;(is (= :player2 (winner "2D 9C AS AH AC 3D 6D 7D TD QD")))
;(is (= :player1 (winner "4D 6S 9H QH QC 3D 6D 7H QD QS")))
;(is (= :player1 (winner "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D")))

;(println (winner "5H 5C 6S 7S KD 2C 3S 8S 8D TD"))

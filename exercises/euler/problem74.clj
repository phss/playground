(use 'commons)
(use 'clojure.test)

(def precalcfacts (apply merge (map (fn [n] {n (factorial n)}) (range 0 10))))

(defn sum-facts [n]
  (let [digits (digits-from n)
        facts (map precalcfacts digits)]
    (reduce + facts)))

(is (= 145 (sum-facts 145)))

(defn count-fact-chain [n]
  (loop [chain [n] next-n (sum-facts n)]
    (if (some #{next-n} chain)
      (count chain)
      (recur (conj chain next-n) (sum-facts next-n)))))

(is (= 5 (count-fact-chain 69)))
(is (= 4 (count-fact-chain 78)))
(is (= 2 (count-fact-chain 540)))

(time (println (count (filter #(= % 60) (map count-fact-chain (range 1 1000000))))))

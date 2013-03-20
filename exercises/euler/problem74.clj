(use 'commons)

(defn sum-facts [n]
  (let [digits (digits-from n)
        facts (map factorial digits)]
    (reduce + facts)))

(println (sum-facts 145))

(defn one-diff? [w1 w2]
  (let [[biggest smallest] (if (> (count w1) (count w2)) [w1 w2] [w2 w1])
        cb (count biggest)
        cs (count smallest)
        letter-diff (fn [a b] (count (filter false? (for [i (range (count a))] (= (get a i) (get b i))))))
        one-letter-removed-combs (fn [word] (for [i (range (count word))] (apply str (concat (take i word) (drop (inc i) word)))))]
    (cond
      (= cb cs) (= 1 (letter-diff biggest smallest))
      (= cb (inc cs)) (some #{smallest} (one-letter-removed-combs biggest))
      :else false)))

(defn word-graph [words]
  (letfn [(related-to [word] (partial one-diff? word))] 
    (map (fn [word] [word (filter (related-to word) words)]) words)))

(defn word-chain [words]
  (let [g (word-graph words)]
    (loop [walks [[(map vector words)]]]
      (let [n (first walks)]
        (cond
          (empty? walks) false
          (= (count n) (count words)) true
          )))))

(println (word-chain #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))


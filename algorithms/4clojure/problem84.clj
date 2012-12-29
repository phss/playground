(defn one-diff? [w1 w2]
  (let [[biggest smallest] (if (> (count w1) (count w2)) [w1 w2] [w2 w1])
        cb (count biggest)
        cs (count smallest)
        combs (fn [word] (for [x (range (count word))] (apply str (concat (take x word) (drop (inc x) word)))))]
    (cond
      (= cb cs) (= 1 (count (filter false? (for [x (range cb)] (= (get biggest x) (get smallest x))))))
      (= cb (inc cs)) (some #(= % smallest) (combs biggest))
      :else false)))

(defn word-graph [words]
  (letfn [(related-to [word] (partial one-diff? word))] 
    (map (fn [word] [word (filter (related-to word) words)]) words)))

(defn word-chain [words]
  (let [g (word-graph words)]
    g))

(println (word-chain #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))

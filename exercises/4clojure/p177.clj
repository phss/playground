
(defn balanced? [s]
  (let [p (clojure.string/replace s #"[^ \(\)\[\]\{\}]" "")]
    (rest (clojure.string/split p #""))))

(println (balanced? "{(abc)}"))
(println (balanced? "{efd[(abc)}]"))

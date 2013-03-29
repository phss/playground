(ns fourclojure.p86)

(defn happy? [number]
  (let [digit #({\0 0, \1 1, \2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9} %)
        digits (fn [n] (map digit (str n)))
        square #(* % %)
        ssd (fn [n] (int (reduce + (map square (digits n)))))]
    (loop [n (ssd number) prev #{}]      
      (cond
        (= n 1) true
        (contains? prev n) false
        :else (recur (ssd n) (conj prev n))))))

(println (happy? 3))

(println (happy? 7))


;(defn happyseq [n]
;  (let [h (happy? n)]
;    (cons h (lazy-seq (happyseq h)))))

;(take 20 (happyseq 3))
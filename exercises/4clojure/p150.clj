(ns fourclojure.p150
  (require clojure.string))

(defn pals [n]
  (let [offset (int (Math/pow 10 (quot (count (str n)) 2)))
        base (int (quot n offset))
        inverted (Integer/parseInt (clojure.string/join (reverse (str (quot base 10)))))]
    (+ (* base offset) inverted)))


(pals 3456)
;(take 16 (pals 345))
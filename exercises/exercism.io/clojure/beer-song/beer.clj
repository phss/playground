(ns beer)

(defn verse [n]
  (let [bottles #(str % " bottles of beer")]
    (str (bottles n) " on the wall, " (bottles n) ".\nTake one down and pass it around, " (bottles (dec n)) " on the wall.\n")))

(defn sing [a b])

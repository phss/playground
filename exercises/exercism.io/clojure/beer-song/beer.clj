(ns beer)

(defn verse [n]
  (let [bottles #(str % " bottles of beer")
        on-the-wall #(str (bottles %) " on the wall")]
    (str (on-the-wall n) ", " (bottles n) ".\nTake one down and pass it around, " (on-the-wall (dec n)) ".\n")))

(defn sing [a b])

(ns superdigit)

(def input "148 3")

(defn split-str [s]
  (clojure.string/split s #" "))

(defn str-to-int [s]
  (Integer/parseInt s))

(map str-to-int (split-str input))


(ns problem1)

(defn split [re s]
  (clojure.string/split s re))

(def directions
  (->> "files/problem1.txt"
       (slurp)
       (clojure.string/trim-newline)
       (split #", ")
       (map (fn [[dir & dist]] [dir (Integer/parseInt (apply str dist))]))))

directions

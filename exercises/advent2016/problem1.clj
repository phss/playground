(ns problem1)

(defn split [re s]
  (clojure.string/split s re))

(def directions
  (->> "files/problem1.txt"
       (slurp)
       (clojure.string/trim-newline)
       (split #", ")
       (map (fn [[dir & dist]] [dir (Integer/parseInt (apply str dist))]))))

(def turn
  {\L {:N :W, :W :S, :S :E, :E :N}
   \R {:N :E, :W :N, :S :W, :E :S}})

(defn move [[x y] dir dist]
  (dir {:N [x (+ y dist)], :W [(- x dist) y], :S [x (- y dist)], :E [(+ x dist) y]}))

(def dest
  (loop [dir :N pos [0 0] dirs directions]
    (if (empty? dirs)
      pos
      (let [[t dist] (first dirs)
            newdir ((turn t) dir)
            newpos (move pos newdir dist)]
        (recur newdir newpos (rest dirs))))))

dest

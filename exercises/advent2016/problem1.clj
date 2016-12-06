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

(defn process-direction [[dir pos] [t dist]]
  (let [newdir ((turn t) dir)
        newpos (move pos newdir dist)]
    [newdir newpos]))

(def path-to-destination
  (reductions process-direction [:N [0 0]] directions))

; Answer to part 1
;(last path-to-destination)

(def repeated-locations
  (->> path-to-destination
       (map last)
       frequencies
       (filter (fn [[_ c]] (> c 1)))))


repeated-locations

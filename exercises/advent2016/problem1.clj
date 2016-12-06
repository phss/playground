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

(defn locations-between [[x1 y1] [x2 y2]]
  (letfn [(between [a b] (range a b (if (< a b) 1 -1)))]
    (concat (map #(vector % y1) (between x1 x2))
            (map #(vector x1 %) (between y1 y2)))))

(def all-locations
  (->> path-to-destination
       (map last)
       (partition 2 1)
       (mapcat #(apply locations-between %))))

(def first-duplicate-location
  (loop [visited [] locs all-locations]
    (let [loc (first locs)]
      (if (some #{loc} visited)
        loc
        (recur (conj visited loc) (rest locs))))))

first-duplicate-location

(ns problem3)

(defn line-to-triangle [line]
  (map
   (fn [n] (Integer/parseInt n))
   (re-seq #"\d+" line)))

(def triangles
  (->> "files/problem3.txt"
       (slurp)
       (clojure.string/split-lines)
       (map line-to-triangle)))

(def actual-triangles
  (partition 3 (concat (map first triangles) (map second triangles) (map last triangles))))

(defn valid-triangle? [[a b c]]
  (and
   (< a (+ b c))
   (< b (+ a c))
   (< c (+ a b))))

(->> actual-triangles
     (filter valid-triangle?)
     count)


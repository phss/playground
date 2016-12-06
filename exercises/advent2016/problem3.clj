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

(defn valid-triangle? [[a b c]]
  (and
   (< a (+ b c))
   (< b (+ a c))
   (< c (+ a b))))

(->> triangles
     (filter valid-triangle?)
     count)

(use 'commons)

(def matrix (->> 
              "files/problem81.txt"
              (slurp)
              (clojure.string/split-lines)
              (map #(map number-from (clojure.string/split % #",")))))

(def test-matrix [
[131	673	234	103	18]
[201	96	342	965	150]
[630	803	746	422	111]
[537	699	497	121	956]
[805	732	524	37	331]]) 

(defn cost
  [m [x y]]
  ((m y) x))

(defn path-cost
  [m path]
  (reduce + (map (partial cost m) path)))

(defn min-path-sum
  [m]
  nil)

(println (cost test-matrix [2 0]))
(println (path-cost test-matrix [[0 0] [1 0] [1 1]]))

;(println (count matrix))
(time (println (min-path-sum test-matrix)))


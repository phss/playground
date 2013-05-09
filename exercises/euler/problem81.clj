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

(defn valid-next-steps
  [m [x y]]
  (let [valid? (fn [[nx ny]] (and (< ny (count m)) (< nx (count (nth m y)))))]
    (filter valid? [[(inc x) y] [x (inc y)]])))

(defn min-path-sum
  [m]
  (let [goal [(dec (count m)) (dec (count m))]]
    (loop [paths [[[0 0]]] explored [[0 0]]]
      (let [[shortest & other] (sort-by (partial path-cost m) paths)
            last-visited (last shortest)]
        (if (= goal last-visited)
          (path-cost m shortest)
          (recur (concat other (map (partial conj shortest) (valid-next-steps m last-visited))) 
                 (conj explored last-visited)))))))


(time (println (min-path-sum test-matrix)))


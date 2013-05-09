(use 'commons)

(def matrix (->> 
              "files/problem81.txt"
              (slurp)
              (clojure.string/split-lines)
              (map #(vec (map number-from (clojure.string/split % #","))))
              (vec)))

(def test-matrix 
  [[131	673	234	103	18]
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
  [m [x y] explored]
  (let [valid? (fn [[nx ny]] (and (< ny (count m))
                                  (< nx (count (nth m y)))
                                  (not-any? #{[nx ny]} explored)))]
    (filter valid? [[(inc x) y] [x (inc y)]])))

(defn min-path-sum
  [m]
  (let [goal [(dec (count m)) (dec (count m))]]
    (loop [paths [[[0 0] (cost m [0 0])]] explored [[0 0]]]
      (let [[[last-visited pc] & other] (sort-by second paths)]
        (if (= goal last-visited)
          pc
          (recur (concat other (map (fn [n] [n (+ pc (cost m n))]) (valid-next-steps m last-visited explored))) 
                 (concat explored (valid-next-steps m last-visited explored))))))))


(time (println (min-path-sum test-matrix)))
(time (println (min-path-sum matrix)))


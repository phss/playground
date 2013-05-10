(use 'commons)

(def matrix (->> 
              "files/problem83.txt"
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

(defn valid-next-steps
  [m [x y]]
  (let [valid? (fn [[nx ny]] (and (< -1 ny (count m)) (< -1 nx (count (nth m y)))))]
    (filter valid? [[(inc x) y] [x (inc y)] 
                    [(dec x) y] [x (dec y)]])))

(defn min-path-sum
  [m]
  (let [initial [0 0]
        goal [(dec (count m)) (dec (count m))]
        cost-of (partial cost m)
        path-to (fn [pos cost-so-far] [pos (+ cost-so-far (cost-of pos))])]
    (loop [paths [path-to initial 0] explored [initial]]
      (let [[[last-visited path-cost] & other] (sort-by second paths)]
        (if (= goal last-visited)
          path-cost
          (let [unexplored? #(not-any? #{%} explored)
                next-steps (filter unexplored? (valid-next-steps m last-visited))
                next-paths (map (fn [n] (path-to n path-cost)) next-steps)] 
            (recur (concat other next-paths) (concat explored next-steps))))))))


(time (println (min-path-sum test-matrix)))
(time (println (min-path-sum matrix)))


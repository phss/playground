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

(defn valid-next-steps
  [m [x y] explored]
  (let [valid? (fn [[nx ny]] (and (< -1 ny (count m))
                                  (< -1 nx (count (nth m y)))
                                  (not-any? #{[nx ny]} explored)))]
    (filter valid? [[(inc x) y] [x (inc y)] [x (dec y)]])))

(defn min-path-sum
  [m]
  (let [initial-places (map (fn [y] [0 y]) (range 0 (count m)))
        goal-column (dec (count m))
        cost-of (partial cost m)]
    (loop [paths (map (fn [i] [i (cost-of i)]) initial-places) explored initial-places]
      (let [[[last-visited path-cost] & other] (sort-by second paths)]
        (if (= goal-column (first last-visited))
          path-cost
          (let [next-steps (valid-next-steps m last-visited explored)
                next-paths (map (fn [n] [n (+ path-cost (cost-of n))]) next-steps)] 
            (recur (concat other next-paths) (concat explored next-steps))))))))


(time (println (min-path-sum test-matrix)))
(time (println (min-path-sum matrix)))


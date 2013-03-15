(use 'commons)
(use 'clojure.test)

(defn perfect-square? [n]
  (let [s (Math/sqrt n)]
    (no-decimal? s)))

(is (false? (perfect-square? 3)))
(is (true? (perfect-square? 4)))

(defn sqrt-period [n]
  (let [a0 (floor (sqrt n))]
   (loop [i [[0 1 a0]]]
    (let [[mp dp ap] (last i)
          m (- (* dp ap) mp)
          d (/ (- n (pow2 m)) dp)
          a (floor (/ (+ a0 m) d))]
      (if (some #{[m d a]} i)
        (rest (map last i))
        (recur (conj i [m d a])))))))

(is (= [2] (sqrt-period 2)))
(is (= [1 1 1 4] (sqrt-period 7)))
(is (= [3 6] (sqrt-period 11)))

(println (->> (range 1 10001)
              (remove perfect-square?)
              (map (comp count sqrt-period))
              (filter odd?)
              (count)))

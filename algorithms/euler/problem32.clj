(use 'clojure.set)

(def max-pan 987654321)
(def min-pan 123456789)

(defn sqrt [n]
  (int (Math/sqrt n)))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)) )

(defn nine-pan? [d]
  (= (range 1 10) (sort d)))

(defn permutations [col]
  (loop [perms (map vector col) level 1]
    (if (= level (count col))
      perms 
      (recur (reduce (fn [next-perms perm]
                       (concat next-perms (map (fn [n] (conj perm n)) (difference (set col) (set perm))))) 
                     [] perms) 
             (inc level)))))


(println (take 5 (permutations (range 1 5))))

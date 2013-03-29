(ns fourclojure.p53)

(defn longest2 [c]
  (loop [coll (rest c) longest [] curr [(first c)]]
    (if (seq coll)
      (if (= (first coll) (inc (last curr))) 
        (let [new_curr (conj curr (first coll))] 
          (recur (rest coll) (if (< (count new_curr) (count longest)) longest new_curr) new_curr))
        (recur (rest coll) longest [(first coll)]))
      longest)))

(defn longest [c]
  (loop [coll (rest c) colls [] curr [(first c)]]
    (if (seq coll)
      (if (= (first coll) (inc (last curr))) 
        (let [new_curr (conj curr (first coll))] 
          (recur (rest coll) (if (< (count new_curr) (count longest)) longest new_curr) new_curr))
        (recur (rest coll) longest [(first coll)]))
      longest)))

(println (longest [1 0 1 2 3 0 4 5]))

(println (longest [5 6 1 3 2 7]))

(println (longest [2 3 3 4 5]))

(println (longest [7 6 5 4]))
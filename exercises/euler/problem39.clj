(defn pow2 [n]
  (* n n))

(defn right-tri? [a b c]
  (= (pow2 c) (+ (pow2 a) (pow2 b))))


(println (right-tri? 20 48 52))
(println (right-tri? 20 48 50))

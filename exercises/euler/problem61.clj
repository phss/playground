(use 'commons)

(defn take-in-range [f]
  (map (fn [n] {:type (str f) :num n}) (drop-while #(> 1000 %) (take-while #(>= 9999 %) (map f (iterate inc 1))))))

(defn tri [n]
  (int (* n (+ 1 n) 0.5)))

(def tris (take-in-range tri))

(defn square [n]
  (* n n))

(def sqs (take-in-range square))

(defn pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (take-in-range pent))

(defn hex [n]
  (* n (- (* 2 n) 1)))

(def hexs (take-in-range hex))

(defn hept [n]
  (int (* n (- (* 5 n) 3) 0.5)))

(def hepts (take-in-range hept))

(defn oct [n]
  (* n (- (* 3 n) 2)))

(def octs (take-in-range oct))

(defn linked? [a b]
  (let [a-link (take-last 2 (digits-from a))
        b-link (take 2 (digits-from b))]
    (= a-link b-link)))

(def polygs (concat sqs pents))

(def cyclics (for [a tris
                   b (filter #(linked? (a :num) (% :num)) polygs)
                   c (filter #(and (not= (b :type) (% :type)) (linked? (b :num) (% :num)) (linked? (% :num) (a :num))) polygs)]
               [a b c]))

(time (println (map :num (first cyclics))))


(println (map count [tris sqs pents hexs hepts octs]))

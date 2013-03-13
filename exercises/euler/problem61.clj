(use 'commons)

(defn take-in-range [f t]
  (map (fn [n] {:type t :num n}) (drop-while #(> 1000 %) (take-while #(>= 9999 %) (map f (iterate inc 1))))))

(defn tri [n]
  (int (* n (+ 1 n) 0.5)))

(def tris (take-in-range tri :tri))

(defn square [n]
  (* n n))

(def sqs (take-in-range square :sq))

(defn pent [n]
  (int (* n (- (* 3 n) 1) 0.5)))

(def pents (take-in-range pent :pent))

(defn hex [n]
  (* n (- (* 2 n) 1)))

(def hexs (take-in-range hex :hex))

(defn hept [n]
  (int (* n (- (* 5 n) 3) 0.5)))

(def hepts (take-in-range hept :hep))

(defn oct [n]
  (* n (- (* 3 n) 2)))

(def octs (take-in-range oct :oct))

(defn linked? [a b]
  (let [a-link (take-last 2 (digits-from (a :num)))
        b-link (take 2 (digits-from (b :num)))]
    (= a-link b-link)))

(defn different-types? [elems]
  (let [types (map :type elems)]
    (apply distinct? types)))

(def polygs (concat tris sqs pents hexs hepts))

(def cyclics (for [a octs
                   b (filter #(linked? a %) polygs)
                   c (filter #(and (different-types? [b %]) (linked? b %)) polygs)
                   d (filter #(and (different-types? [b c %]) (linked? c %)) polygs)
                   e (filter #(and (different-types? [b c d %]) (linked? d %)) polygs)
                   f (filter #(and (different-types? [b c d e %]) (linked? e %) (linked? % a)) polygs)]
               [a b c d e f]))

(time (println (map :num (first cyclics))))
(time (println (map :type (first cyclics))))
(println (reduce + (map :num (first cyclics))))

(println (map count [tris sqs pents hexs hepts octs]))

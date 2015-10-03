(ns superdigit)

(defn split-str [s]
  (clojure.string/split s #" "))

(defn str-to-int [s]
  (Integer/parseInt s))

(def input (split-str "148 3"))
(def initial-num (reduce str (repeat (str-to-int (last input)) (first input))))

(defn sum-of-digits [n]
  (->> (seq n)
       (map str)
       (map str-to-int)
       (reduce +)
       (str)))

(def solution
  (->> (iterate sum-of-digits initial-num)
       (filter #(= 1 (count %)))
       (first)))

(println solution)

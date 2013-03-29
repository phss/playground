(ns fourclojure.p102
  (:require clojure.string))

;(clojure.string/join (into [base] (map clojure.string/capitalize more)))
(defn caseit [s]
  (let [strings (clojure.string/split s #"-")
        join-camel (fn [[base & more]] (clojure.string/join (into [base] (map clojure.string/capitalize more))))] 
    (join-camel strings)))

(println (caseit "something"))

(println (caseit "multi-word-key"))

(println (caseit "leaveMeAlone"))
(ns fourclojure.p56)

(defn dist [s]
  (reduce (fn [a e] (if (not (some #{e} a)) (conj a e) a)) [] s))

(dist [:a :a :b :b :c :c])
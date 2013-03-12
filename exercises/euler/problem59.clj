(use 'commons)

(def message (map (comp to-int clojure.string/trim) (clojure.string/split (slurp "files/problem59.txt") #",")))

(def char-codes (concat (range (int \A) (inc (int \Z))) (range (int \a) (inc (int \z)))))

(defn stringify [m]
  (apply str (map char m)))

(println char-codes)

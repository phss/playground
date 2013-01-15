(defn transpose [matrix]
  (let [n (count (matrix 0))]
    (for [i (range n)] (map #(get % i) matrix))))

(defn profile-matrix [dnas]
  (let [tdnas (transpose dnas)
        syms [\A \C \G \T]
        count-sym (fn [sym] (map #(count (filter #{sym} %)) tdnas))]
    (map (fn [sym] {sym (count-sym sym)}) syms)))

(def dnas
"ATCCAGCT
GGGCAACT
ATGGATCT
AAGCAACC
TTGGAACT
ATGCCATT
ATGGCACT")

(println (profile-matrix (clojure.string/split-lines dnas)))

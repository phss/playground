(defn transpose [matrix]
  (let [n (count (matrix 0))]
    (for [i (range n)] (map #(get % i) matrix))))

(defn profile-matrix [dnas]
  (let [tdnas (transpose dnas)
        syms [\A \C \G \T]
        count-sym (fn [sym] (map #(count (filter #{sym} %)) tdnas))]
    (map (fn [sym] [sym (count-sym sym)]) syms)))

(defn print-pm [matrix]
  (doseq [[sym occurs] matrix]
    (println (format "%s: %s" sym (clojure.string/join " " occurs)))))

(defn consensus [dnas]
  (let [tdnas (transpose dnas)
        group-and-count (fn [dna] (map (fn [[k v]] [k (count v)]) (group-by identity dna)))
        gm (map #(sort-by second > (group-and-count %)) tdnas)]
    (clojure.string/join "" (map (comp first first) gm))))

(def dnas
"ATCCAGCT
GGGCAACT
ATGGATCT
AAGCAACC
TTGGAACT
ATGCCATT
ATGGCACT")

(def m (clojure.string/split-lines dnas))
(def pm (profile-matrix m))

(println (consensus m))
(print-pm pm)

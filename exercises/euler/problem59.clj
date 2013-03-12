(use 'commons)

(def message (map (comp to-int clojure.string/trim) (clojure.string/split (slurp "files/problem59.txt") #",")))

(def lower-case-codes (range (int \a) (inc (int \z))))
(def char-codes (concat (range (int \A) (inc (int \Z))) lower-case-codes))

(defn valid? [c]
  (some #{c} char-codes))

(defn stringify [m]
  (apply str (map char m)))

(def indexes (group-by #(mod % 3) (range (count message))))

(defn possible-keys-for [idxs]
  (filter (fn [code]
            (every? (fn [idx] (valid? (bit-xor code (nth message idx)))) idxs)) 
          lower-case-codes))


(def possible-keys (map (fn [[k v]] [k (possible-keys-for v)]) indexes))

(println possible-keys)

(println char-codes)
(println (possible-keys-for [0]))

(use 'commons)

(def message (map (comp to-int clojure.string/trim) (clojure.string/split (slurp "files/problem59.txt") #",")))

(def lower-case-codes (range (int \a) (inc (int \z))))

(defn stringify [m]
  (apply str (map char m)))

(defn decrypt-block [k b])

(defn decrypt [k msg]
  (let [decryption-blocks (partition-all (count k) msg)
        decrypt-block (fn [block] (map bit-xor k block))]
    (mapcat decrypt-block decryption-blocks)))

(println (stringify (decrypt [110 111 112] message)))

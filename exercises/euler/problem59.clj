(use 'commons)

(def message (map (comp to-int clojure.string/trim) (clojure.string/split (slurp "files/problem59.txt") #",")))

(def lower-case-codes (range (int \a) (inc (int \z))))
(def search-word (map int "that"))

(defn contain-search-word? [msg]
  (let [blocks (partition-all (count search-word) 1 msg)]
    (some #{search-word} blocks)))

(defn stringify [m]
  (apply str (map char m)))

(defn decrypt-block [k b])

(defn decrypt [k msg]
  (let [decryption-blocks (partition-all (count k) msg)
        decrypt-block (fn [block] (map bit-xor k block))]
    (mapcat decrypt-block decryption-blocks)))

(def candidates (for [a lower-case-codes
                      b lower-case-codes
                      c lower-case-codes
                      :let [k [a b c]
                            decrypted (decrypt k message)]
                      :when (contain-search-word? decrypted)]
                  [k decrypted]))

;(println (map (fn [[k d]] [k (stringify d)]) candidates))

(println (stringify (decrypt [103 111 100] message)))
(println (reduce + (decrypt [103 111 100] message)))

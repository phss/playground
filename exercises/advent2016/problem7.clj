(ns problem7)

(def ips
  (->> "files/problem7.txt"
       (slurp)
       (clojure.string/split-lines)))

(defn parse-ip [ip]
  (loop [chars ip mode :seqs seqs [""] hypernet-seqs [""]]
    (let [[curr & rem] chars
          add-to-last (fn [col e] (update-in col [(dec (count col))] #(str % e)))]
      (cond
        (empty? chars) [seqs hypernet-seqs]
        (= curr \[) (recur rem :hypernet (conj seqs "") hypernet-seqs)
        (= curr \]) (recur rem :seqs seqs (conj hypernet-seqs ""))
        (= mode :seqs) (recur rem mode (add-to-last seqs curr) hypernet-seqs)
        (= mode :hypernet) (recur rem mode seqs (add-to-last hypernet-seqs curr))))))

(defn abba? [[a b c d]]
  (and (= a d) (= b c) (not= a b)))

(defn tls? [[seqs hypernet-seqs]]
  (letfn [(any-is-abba? [col]
            (some abba? (mapcat (partial partition 4 1) col)))]
    (and (any-is-abba? seqs)
         (not (any-is-abba? hypernet-seqs)))))

(->> ips
     (map parse-ip)
     (filter tls?)
     count)


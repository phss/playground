(ns fourclojure.p69)

(defn mergef [f base & more]
  (let [merge-in (fn [b coll]
                   (reduce (fn [acc [k v]] (if (contains? acc k) (update-in acc [k] f v) (conj acc [k v]))) b coll))]
    (reduce merge-in base more)))

(mergef * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5} {:d 10})
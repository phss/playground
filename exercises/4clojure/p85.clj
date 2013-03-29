(ns fourclojure.p85)

(defn powerset [set]
  (if (= set #{}) #{#{}}
	  (loop [s [set] super #{#{} set}]
	    (let [combinations (fn [coll] (map #(clojure.set/difference coll #{%}) coll))
	          ssets (reduce (fn [acc e] (into acc (combinations e))) #{} s)]
	      (if (= #{} (first ssets))
	        super
	        (recur ssets (into super ssets)))
	      ))))

(println (powerset #{1 2 3}))

(println (powerset #{}))
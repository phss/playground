(ns spoj.test)

(defn read-int [] (Integer/parseInt (read-line)))

(defn life []
  (let [n (read-int)]
	  (when (not= 42 n)
	    (println n)
	    (recur))))

(life)

;(life [1 2 88 42 99])


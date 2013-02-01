
(def coins [1 2 5 10 20 50 100 200])

(defn ways-to-make [value]
  (loop [ways [] values [value]]
    (if (empty? values)
      (count ways)
      (recur (concat ways [[1 200] [200 1]]) []))))

(time (println (ways-to-make 200)))

(use 'commons)

(def rpf (for [d (range 2 1001)
               n (range 1 d)
               :when (= 1 (gcd n d))]
            [n d]))

(defn count-rpf-to [n]
  (loop [c 0 d 2]
    (if (= n d)
      c
      (let [rpfs (filter #(= 1 (gcd % d)) (range 1 d))]
        (recur (+ c (count rpfs)) (inc d))))))

(time (println (count-rpf-to 1000001)))

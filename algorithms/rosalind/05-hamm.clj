(defn hamming-distance [s t]
  (count (filter false? (for [x (range (count s))] (= (get s x) (get t x))))))

(def a "GAGCCTACTAACGGGAT")
(def b "CATCGTAATGACGGCCT")

(println (hamming-distance a b))

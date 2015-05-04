(ns combinatorics)

(defn- pop-index [col index]
  [(col index)
   (vec (concat (subvec col 0 index) (subvec col (inc index))))])

(defn all-thingies [col]
  (map #(pop-index col %) (range (count col))))

(defn all-permutations [col]
  (all-thingies col))

(all-permutations [1 2 3 4])

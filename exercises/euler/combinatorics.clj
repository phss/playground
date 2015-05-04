(ns combinatorics)

(defn- split-at-index [col index]
  [(col index)
   (vec (concat (subvec col 0 index) (subvec col (inc index))))])

(defn- all-splits [col]
  (map (partial split-at-index col) (range (count col))))

(defn- next-permutation-level [permutations]
  (for [[perm next-options] permutations
        [option next-next-options] (all-splits next-options)]
    [(conj perm option) next-next-options]))

(defn all-permutations [col]
  (let [initial-perm [[[] col]]
        levels (count col)]
    (map first (nth (iterate next-permutation-level initial-perm) levels))))

(defn permutations-by-n [col n]
  (let [next-level (fn [perms] (for [p perms e col] (conj p e)))]
    (nth (iterate next-level [[]]) n)))


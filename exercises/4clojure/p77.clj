(ns fourclojure.p77)

(defn ana [words]
  (set (map set (filter #(< 1 (count %)) (vals (group-by sort words))))))

(ana ["veer" "lake" "item" "kale" "mite" "ever"])

(ana ["meat" "mat" "team" "mate" "eat"])
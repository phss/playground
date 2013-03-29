(ns fourclojure.p98)

(defn eqclass [f d]
  (let [groups (vals (group-by first (map (fn [n] [(f n) n]) d)))]
    (set (map (fn [g] (set (map second g))) groups))))

(eqclass #(* % %) #{-2 -1 0 1 2})
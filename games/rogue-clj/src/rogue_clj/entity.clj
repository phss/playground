(ns rogue-clj.entity)

(defn make-entity [t pos]
  {:type t, :position pos})

(defn x [entity]
  (get-in entity [:position :x]))

(defn y [entity]
  (get-in entity [:position :y]))

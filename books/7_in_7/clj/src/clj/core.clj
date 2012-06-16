(ns clj.core)

; Day 1 exercises
(defn big [st n] (> (count st) n))

(defn collection-type [col] 
  (if (list? col)
    :list
    (if (map? col)
      :map
      (if (vector? col)
        :vector
        nil))))

(defn cond-collection-type [col]
  (cond
    (list? col) :list
    (map? col) :map
    (vector? col) :vector
  )

; Hacky!!
(defmulti multi-collection-type class)
(defmethod multi-collection-type (class []) [x] :vector)
(defmethod multi-collection-type (class '()) [x] :list)
(defmethod multi-collection-type (class {}) [x] :map)
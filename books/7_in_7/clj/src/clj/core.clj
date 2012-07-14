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
)

; Hacky!!
(defmulti multi-collection-type class)
(defmethod multi-collection-type (class []) [x] :vector)
(defmethod multi-collection-type (class '()) [x] :list)
(defmethod multi-collection-type (class {}) [x] :map)

; Day 2 compass
(def directions [:north :east :south :west])

(defn turn
  [base amount]
  (rem (+ base amount) (count directions)))


(defprotocol Compass
  (direction [c])
  (left [c])
  (right [c]))

(defrecord SimpleCompass [bearing]
  Compass
  (direction [_] (directions bearing))
  (left [_] (SimpleCompass. (turn bearing 3)))
  (right [_] (SimpleCompass. (turn bearing 1)))

  Object
  (toString [this] (str "[" (direction this) "]" ))
)
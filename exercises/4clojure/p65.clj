(ns fourclojure.p65)

(defn type? [col]
  (cond
    (and (associative? col) (not (reversible? col))) :map
    (= 1 (:test (frequencies (conj col :test :test)))) :set
    (= :test (last (conj col :notme :test))) :vector
    (= :test (first (conj col :notme :test))) :list
    :else :no))

(println (type? {:a 1, :b 2}))

(println (type? (range (rand-int 20))))

(println (type? [1 2 3 4]))

(println (type? #{:a :b}))

(map type? [#{} [] () {}])
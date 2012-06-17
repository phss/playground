(use 'clojure.test)

(defn atom? [x] (not (coll? x))) ; Not quite correct, but it will do

(defn null? [x] 
  (and (not (atom? x))
       (empty? x))
)

(defn lat? [l] (every? atom? l))

(defn member? [a lat] (some (partial = a) lat))

(defn append [elem l] (concat l (list elem)))

; add1
(defn add1 [n] (+ n 1))

(is (= (add1 42) 43))
(is (= (add1 68) 69))

; sub1
(defn sub1 [n] (- n 1))

(is (= (sub1 42) 41))
(is (= (sub1 68) 67))

; zero?
(is (zero? 0))

(is (not (zero? 1)))

; plus
(defn plus [x y]
  (loop [ny y sum x]
    (cond 
      (zero? ny) sum
      :else (recur (sub1 ny) (add1 sum)))
  )
)

(is (= (plus 46 12)
    58
))

; minus
(defn minus [x y]
  (loop [ny y dif x]
    (cond 
      (zero? ny) dif
      :else (recur (sub1 ny) (sub1 dif)))
  )
)

(is (= (minus 46 12)
    34
))

; addtup
(defn addtup [lat]
  (loop [l lat sum 0]
    (cond 
      (null? l) sum
      :else (recur (rest l) (plus sum (first l))))
  )
)

(is (= (addtup '(3 5 2 8))
    18
))

(is (= (addtup '(15 6 7 12 3))
    43
))

; multi
(defn multi [x y]
  (loop [ny y m 0]
    (cond 
      (zero? ny) m
      :else (recur (sub1 ny) (plus m x)))
  )
)

(is (= (multi 13 4)
    52
))

(is (= (multi 13 1)
    13
))

; tup+

(defn tup+ [tup1 tup2]
  (loop [t1 tup1 t2 tup2 tsum '()]
    (cond
      (null? t1) (concat tsum t2) 
      (null? t2) (concat tsum t1)
      :else (recur (rest t1) 
                   (rest t2) 
                   (append (plus (first t1) (first t2)) tsum)))
  )
)

(is (= (tup+ '(3 6 9 11 4) '(8 5 2 0 7))
    '(11 11 11 11 11)
))

(is (= (tup+ '(2 2) '(1 1 1 1 1))
    '(3 3 1 1 1)
))

(is (= (tup+ '(1 1 1 1 1) '(2 2))
    '(3 3 1 1 1)
))

; more (>)
(defn more [x y]
  (loop [nx x ny y]
    (cond
      (zero? nx) false
      (zero? ny) true
      :else (recur (sub1 nx) (sub1 ny)))
  )
)

(is (not (more 13 133)))

(is (not (more 13 13)))

(is (more 120 11))

; less (<)
(defn less [x y]
  (loop [nx x ny y]
    (cond
      (zero? ny) false
      (zero? nx) true
      :else (recur (sub1 nx) (sub1 ny)))
  )
)

(is (less 13 133))

(is (not (less 13 13)))

(is (not (less 120 11)))

; power
(defn power [x y]
  (loop [ny y pow 1]
    (cond 
      (zero? ny) pow
      :else (recur (sub1 ny) (multi pow x)))
  )
)

(is (= (power 1 1)
    1
))

(is (= (power 5 3)
    125
))

; pick
; clojure
; (defn pick [n lat] (nth lat (sub1 n)))

; with recur
(defn pick [n lat]
  (loop [l lat i (sub1 n)]
    (cond
      (zero? i) (first l)
      :else (recur (rest l) (sub1 i)))
  )
)

(is (= (pick 4 '(lasagna spaghetti ravioli macaroni meatball))
    'macaroni
))

; rempick
(defn rempick [n lat]
  (loop [l lat i (sub1 n) nl '()]
    (cond
      (zero? i) (concat nl (rest l))
      :else (recur (rest l) (sub1 i) (append (first l) nl)))
  )
)

(is (= (rempick 3 '(hotdogs with hot mustard))
    '(hotdogs with mustard)
))

; no-nums
; clojure
; (defn no-nums [lat] (filter (complement number?) lat))

; recur
(defn no-nums [lat]
  (loop [l lat nl '()]
    (cond
      (null? l) nl
      (number? (first l)) (recur (rest l) nl)
      :else (recur (rest l) (append (first l) nl)))
  )
)

(is (= (no-nums '(5 pears 6 prunes 9 dates))
    '(pears prunes dates)
))

; test template
(is (= ()
    '()
))

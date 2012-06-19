(use 'clojure.test)

(defn atom? [x] (not (coll? x))) ; Not quite correct, but it will do

(defn null? [x] 
  (and (not (atom? x))
       (empty? x))
)

(defn lat? [l] (every? atom? l))

(defn member? [a lat] (some (partial = a) lat))

; rember - remove a member
; by the book 
;(defn rember [a lat]
;  (cond
;    (null? lat) '()
;    (= a (first lat)) (rest lat)
;    :else (cons (first lat) (rember a (rest lat))))
;)

; by the book - using loop/recur
(defn append [elem l] (concat l (list elem)))

(defn rember [a lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= a head) (concat nl tail)
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (rember 'mint '(lamb chops and mint jelly))
    '(lamb chops and jelly)
))

(is (= (rember 'mint '(lamb chops and mint flavored mint jelly))
    '(lamb chops and flavored mint jelly)
))

(is (= (rember 'toast '(bacon lettuce and tomato))
    '(bacon lettuce and tomato)
))

; firsts
; by the book
;(defn firsts [l]
;  (cond
;    (null? l) '()
;    :else (cons (first (first l)) (firsts (rest l))))
;)

; by the book - loop/recur
;(defn firsts [li]
;  (loop [l li nl '()]
;    (cond
;      (null? l) nl
;      :else (recur (rest l) (append (first (first l)) nl)))
;  )
;)

; using clojure functions
(defn firsts [l] (map first l))

(is (= (firsts '((apple peach pumpking) (plum pear cherry) (grape raisin pea) (bean carrot eggplant)))
    '(apple plum grape bean)
))

(is (= (firsts '((a b) (c d) (e f)))
    '(a c e)
))

(is (= (firsts '())
    '()
))

(is (= (firsts '((five plums) (four) (eleven green oranges)))
    '(five four eleven)
))

(is (= (firsts '(((five plums) four) (eleven green orangs) ((no) more)))
    '((five plums) eleven (no))
))

; insertR
(defn insertR [n o lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= o head) (concat nl (concat (list o n) tail))
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (insertR 'c 'b '(a b d e))
    '(a b c d e)
))

; insertL
(defn insertL [n o lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= o head) (concat nl (cons n l))
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (insertL 'c 'd '(a b d e))
    '(a b c d e)
))

; subst
(defn subst [n o lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= o head) (concat nl (cons n tail))
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (subst 'x 'd '(a b d e))
    '(a b x e)
))

; multirember
(defn multirember [a lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= a head) (recur tail nl)
        :else (recur tail (append head nl)))
    )
  )
)
(is (= (multirember 'c  '(a b c d c e c))
    '(a b d e)
))

; multinsertR
(defn multinsertR [n o lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= o head) (recur tail (concat nl (list o n)))
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (multinsertR 'c 'b '(a b d b e))
    '(a b c d b c e)
))

; multinsertL
(defn multinsertL [n o lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= o head) (recur tail (concat nl (list n o)))
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (multinsertL 'c 'd '(a d b d e))
    '(a c d b c d e)
))

; multisubst
(defn multisubst [n o lat]
  (loop [l lat nl '()]
    (let [head (first l) tail (rest l)]
      (cond
        (null? l) nl
        (= o head) (recur tail (append n nl))
        :else (recur tail (append head nl)))
    )
  )
)

(is (= (multisubst 'x 'd '(a b d e d d))
    '(a b x e x x)
))

; test template
(is (= ()
    '()
))

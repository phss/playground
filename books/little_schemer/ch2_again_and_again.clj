(use 'clojure.test)

(defn atom? [x] (not (coll? x))) ; Not quite correct, but it will do

(defn null? [x] 
  (and (not (atom? x))
       (empty? x))
)

; lat? - list of atoms?
; by the book implementation (no tail call recursion, so it's a problem)
;(defn lat? [l]
;  (cond
;    (null? l) true
;    (atom? (first l)) (lat? (rest l))
;    :else false)
;)

; by the book using loop/recur
;(defn lat? [nl]
;  (loop [l nl]
;    (cond
;      (null? l) true
;      (atom? (first l)) (recur (rest l))
;      :else false)
;  )
;)

; using clojure funcs
(defn lat? [l] (every? atom? l))

(is (lat? '(bacon and eggs)))

(is (not (lat? '(bacon (and eggs)))))

; member?
; by the book with loop/recur
;(defn member? [a lat]
;  (loop [l lat]
;    (cond 
;      (null? l) false
;      :else (or (= a (first l))
;                (recur (rest l))))
;  ) 
;)

; using clojure funcs
(defn member? [a lat] (some (partial = a) lat))

(is (member? 'meat '(mashed potatos meat gravy)))

(is (not (member? 'liver '(bagels and lox))))

; test template
(is (= ()
    '()
))

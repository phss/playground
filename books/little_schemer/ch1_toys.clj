(use 'clojure.test)

(defn atom? [x] (not (coll? x))) ; Not quite correct, but it will do

(defn null? [x] 
  (and (not (atom? x))
       (empty? x))
)

;(defmacro invalid? [expression] `(clojure.test/thrown? IllegalArgumentException ~expression))

; Atoms and list
(is (atom? 'turkey))

(is (not (atom? '(x y z))))

(is (not (list? 'turkey)))

(is (list? '(x y z)))

; list manipulation
; first
(is (= (first '(a b c)) 
       'a))

(is (= (first '((x y) a b c)) 
       '(x y)))

(is (thrown? IllegalArgumentException (first 'hotdog)))

; rest
(is (= (rest '(a b c)) 
       '(b c)))

(is (= (rest '((x y) a b c)) 
       '(a b c)
))

(is (= (first (rest '((b) (x y) ((c))))) 
       '(x y)
))

(is (= (rest (rest '((b) (x y) ((c))))) 
       '(((c)))
))
    
; cons - prepending to a list
(is (= (cons 'peanut '(butter and jelly))
       '(peanut butter and jelly) 
))

(is (= (cons '(banana and) '(peanut butter and jelly))
    '((banana and) peanut butter and jelly)
))    

(is (= (cons '(a b (c)) '())
    '((a b (c)))
))

(is (thrown? IllegalArgumentException (cons 'a 'b)))

(is (= (cons 'a (first '((b) c d)))
    '(a b)
))

(is (= (cons 'a (rest '((b) c d)))
    '(a c d)
))

; null - empty list
(is (null? '()))

(is (not (null? '(a b c))))

(is (not (null? 'a)))

; eq? - =

(is (= 'harry 'harry))

(is (not= 'butter 'margarine))


(is (not= '6 '7))

(is (= (first '(mary has a little lamb)) 'mary))

;(def l '(beans beans we need jelly beans))
(let [l '(beans beans we need jelly beans)]
   (is (= (first l) (first (rest l)))))

; test template
(is (= ()
    '()
))

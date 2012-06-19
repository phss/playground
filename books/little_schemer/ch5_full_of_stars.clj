(use 'clojure.test)

(defn atom? [x] (not (coll? x))) ; Not quite correct, but it will do

(defn null? [x] 
  (and (not (atom? x))
       (empty? x))
)

(defn lat? [l] (every? atom? l))

(defn member? [a lat] (some (partial = a) lat))

(defn append [elem l] (concat l (list elem)))

; rember*
; (defn rember* [a l]
;   (loop [sl l nl '()]
;     (let [f (first sl) r (rest sl)]
;       (cond
;         (null? sl) nl
;         (= a f) (recur r nl)
;         (atom? f) (recur r (append f nl))
;         :else (recur r (append (rember* a f) nl)))
;     )
;   )
; )
(defn rember* [a l] (filter #(not= a %) l))

(is (= (rember* 'cup '((coffee (cup)) cup ((tea) cup) (and (hick)) cup))
    '((coffee ()) ((tea)) (and (hick)))
))

; test template
(is (= ()
    '()
))

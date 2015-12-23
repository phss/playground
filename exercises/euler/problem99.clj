(ns problem99
  (:use [commons]))

(defn close= [a b]
  (let [delta 0.001
        diff (- a b)]
    (< (- delta) diff delta)))

(defn nthroot [number n]
  (let [precision 0.000000001]
    (loop [x (/ number 2.0)]
      (let [nextx (/ (+ (* (dec n) x) (/ number (pow x (dec n)))) n)]
        (println nextx)
        (if (close= x nextx) nextx (recur nextx))))))

(println (nthroot 2.0 11.0))

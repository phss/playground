(use 'commons)

(defn pow [n e]
  (int (Math/pow n e)))

(defn digits-count [n]
  (count (digits-from n)))

(println (digits-count (pow 9 3)))

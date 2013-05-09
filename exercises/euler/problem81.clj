(use 'commons)

(def matrix (->> 
              "files/problem81.txt"
              (slurp)
              (clojure.string/split-lines)
              (map #(map number-from (clojure.string/split % #",")))))

(def test-matrix [
[131	673	234	103	18]
[201	96	342	965	150]
[630	803	746	422	111]
[537	699	497	121	956]
[805	732	524	37	331]]) 

(println (count matrix))
(println (count test-matrix))

(use 'commons)

(def boundary 2000000)

(def natnum-sum-tuples (map vector (range) (reductions + (range))))

(println (take 10 natnum-sum-tuples))

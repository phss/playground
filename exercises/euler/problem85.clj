(use 'commons)

(def boundary 2000000)

(def natnum-sum-tuples (map vector (range) (reductions + (range))))

(def combs (for [[p q] natnum-sum-tuples
                 [r s] natnum-sum-tuples
                 :let [area (* p r)
                       rects (* q s)]
                 :while (and (<= r p) (<= rects boundary)) ]
             [area rects]))

(println (take 10 combs))

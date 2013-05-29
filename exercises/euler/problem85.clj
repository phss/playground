(use 'commons)

(def boundary 2000000)

(def natnum-sum-tuples (map vector (range) (reductions + (range))))

(def combs (for [[p q] natnum-sum-tuples :while (<= q boundary)
                 [r s] natnum-sum-tuples :while (<= r p)
                 :let [area (* p r) rects (* q s)]
                 :when (<= rects boundary)]
             [area rects]))

(time (println (last (sort-by second combs))))

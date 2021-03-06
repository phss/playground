(require '[clojure.string :as st])

(defn count-subs [dna t]
  (for [i (range (- (count dna) (count t)))
        :let [s (subs dna i (+ i (count t)))]
        :when (= s t)]
    (inc i)))

(def a "CATATCGGTCCATATCGTCCGCCCATATCGCCATATCGTTCATATCGTCATATCGCTTACATATCGCATGTTCATATCGCCATATCGCATATCGCCATATCGACTCCGTCATATCGCCATCAAGTAACCTGCATATCGACCATATCGTCCCATATCGCATATCGCATATCGGTCTCCGCATATCGTCCATATCGACAGCATATCGCGCAGTACATATCGCATATCGCCCATATCGTCATATCGCCATATCGGTCATATCGCGCGCACAACCCATATCGCATATCGTTCCATATCGTGCCACATATCGCGCATATCGGACATATCGGCACATATCGGTAATAATGCACATATCGCATATCGCTCGCCATATCGCATATCGGTACACATATCGGTGTCCATATCGGCCATATCGGCCTTAATTTCATATCGAGCATATCGCATATCGACATATCGGCATATCGATGTGGCCGTCATATCGGCATATCGCATATCGCATATCGGCATATCGCATATCGCATATCGCATATCGGCATATCGCCATATCGCTCCATATCGCATATCGACATATCGTCACATATCGCATATCGCATATCGATGTACATATCGCATATCGACACATATCGAGTATGCATATCGTGCATATCGCTCGAAGGAACATATCGCATATCGTCATATCGCCATATCGCAAGCACATATCGCATATCGCATATCGGCGCATATCGACTCACTCCAGCATATCGCAAATTACATATCGAGACTCATATCGCAGACATATCGCATATCGCATATCGACGACATATCGGCATATCGCATATCGTTTGCGC")
(def b "CATATCGCA")

(println (st/join " " (count-subs a b)))

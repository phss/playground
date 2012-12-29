(require '[clojure.string :as st])

(defn split-records [ds]
  (let [raw-records (map st/split-lines (rest (st/split ds #">")))]
    (map (fn [raw] {:id (first raw), 
                    :dna (st/join (rest raw))}) 
         raw-records)))

(defn gc-content [record]
  (let [dna (record :dna)
        total-symbols (count dna)
        gc-symbols (count (re-seq #"G|C" dna))]
    (float (/ gc-symbols total-symbols))))

(defn print-highest-gc [ds]
  (let [records (split-records ds)
        highest (last (sort-by gc-content records))
        percent-format #(str (* 100 %) "%")]
    (println (highest :id))
    (println (percent-format (gc-content highest)))))

(def ds-sample
">Rosalind_6404
CCTGCGGAAGATCGGCACTAGAATAGCCAGAACCGTTTCTCTGAGGCTTCCGGCCTTCCC
TCCCACTAATAATTCTGAGG
>Rosalind_5959
CCATCGGTAGCGCATCCTTAGTCCAATTAAGTCCCTATCCAGGCGCTCCGCCGAAGGTCT
ATATCCATTTGTCAGCAGACACGC
>Rosalind_0808
CCACCCTCGTGGTATGGCTAGGCATTCAGGAACCGGAGAACGCTTCAGACCAGCCCGGAC
TGGGAACCTGCGGGCAGTAGGTGGAAT")

(def ds
">Rosalind_9920
AATGTGTTTCAACGATTCCACCCTTTGTTCACAACAGGGGCTTGCGAGTAGGTCCGCGGC
ATTTTCGGCCCGCAAGCATCAACCGACTAAACGTGAGCGGATACGCTTGCTCACACATAA
CCTGTCCGTAGCCCTTGGCAACACGTCCGCGACCTTTCTACTGTCTATATGATAGAACTC
GTATCAAATCAACGTGATTATGCCCACGGGGATTCAGATGTTGTCGACGCCTCATTGCAT
TAGGAAAACGAATGACTACCGAGGAGGAACTGTCCGGGGGTCACACTCCCATATGTCCGC
CGGCGCTTTCGATCTCTGTTACCTTTGAAGAGCCCAAAGTAACTACTAACCTTAGCAAAT
AGAAAACCGAACGAGGCCACAAGTTCTGCGATCCTTCTCTATGTACACATGTCCGGATGC
TGCCCATTTTGGCTTCGCATGATGAGGGCGGAAAAGGCCGGCGTTGGCGATTCATCACCA
CGAATCCACTTGCTGTTAATTTGACAGGATATATCCTGCTATGCTCGATTCGACATGGCC
TAGAGCTGACGAGTCACTATGGTGACAACATGGCGCGATATGTGAAGCACAGCCCCGTCG
TGTAGTTGATAAAATTGTCCCTTCGTCTACTGTAGCCGCCTTGCCAGGTGACGTGATGAG
CCTGGCAGCACAGATCTACCGTCAACAATTCAAGTTCGTGAGGTTTTCGCGGGCTTTGAG
CCACAAGCGTTTATTTATCCCACCGACATAATACGCACTTTTATTTTCCTAGTACTTAAC
AAAAACACTCGCTCAGCAGTCTAGTCTTCATCGGCCCGACTTCATGTGCTCCGCCGATCA
CCAGACACCT
>Rosalind_9688
AGTGTCATGTATTCAGTTGTCCTCCTTACGCAACTCAAAACATGGGAGAATAGCTCACCG
GGCGTCATCCCAAAAGAGTTTCGGCAAGAAGCGTCAATAACGGCTCCTACGAAAGGGATG
ATTGGTGAAGGCTCGGCACTGCTACGCGTAGTGGGCCTATTATGGATTCTTTGTAATCGC
TAAAGGGGCTGGAGACCCAATGCGATATCATTTCCACGAGCAGCCGGGAGGAGCCCCAGT
GAACAAATCGTCCCCGGCTAGTATCGTAGAATCATGTCCCTACTTAGTAATCGCTTCTGC
CTTAAAGGCGGGTGGTCCGCAGAAGATTGCACGAGAGCCGACACGTTTACTCTTGGATAG
ATCAACTGTCTTGTTCATATGCTCTATTGGCCGCTGGTCATTGATCGCCCTGTTTGGGGC
GGTGGGGTAACATAAATATCTGGAAACCTGGAGCAAAGGTGATTATGTCTCAACGACATC
TACGATGTTAGCACCTCCAGTAAACACCGCTGTGCACATACATTAAAACTCTATGTAAAC
AGGCATCGAATGTATGAGAACTGCGAGTAATTCGCGATGCATTTGCCATGGCATTCCTTA
TTTACCATCGTAGATCATAGTTCCCACTAGCTCTTCCCGACGAAACTCTCTCGCACGGGT
GAAGTCACGGAACAGGCAATCTTAAGGGCCGCCATAAAATCGCGAATACCGAGAAACCAA
GGCGGTAAGCAGGAATTCACAATCGTATATCCTTACACTCTCGCTACCTGCAGGCGATCG
TAGACTGAATGACCTAAAACATCACAAGGAGAGCGATTTCTCGACGTTGATAACGTGAAC
>Rosalind_3372
CCTCTCCATCCTTGTGAACGACCGTGTACGACGATACCTCTCCCATTGTTACGACTAAAG
AGTGCCGCCCACTAGTTGGGCACGGTTAACTGAATGTTTCAAAGGGGGATACAAACCCTA
CGTGGACTGTCGTCCGATAGCCGAGAGTGGGCAAAGCAAAGTTAAACTCAGAGTGGCGGT
GGAAGGCGCCTAAATAACAAGAAGCGAATGAGAAGCCACGAGGTGATATGAATAGTTACC
CGCGGCGAATCAGATATTCGGCCTGAGTTTACGGAGTGTGCCTGCAAGATCCATTAGGAG
TCCGCTTATGTACAGGAATAGCTGTTGGTTCCGTAGAAACGCAGGTCCTTACTCGGTGGA
GTGACGCCAAAGCGTGACACACCACGTCTCTCATCGTGGCCCTATAAAATTTACGAAGAA
GCCGCCAGGTCCGTGGTTTCCATGGACCCCGTGAGAAGAGCGAGTCTGCCAGAGCGCAGC
GAACGGGGAAATTAGATGGTCAGGCATCCACATGGCACGCGCAAGTCCGTCTTTGATGGC
ACGTACATGTAAAGTCGGAGCGCCCCGGATTTTCTGCGGACCAGTGAAAGGCTCGCTAGA
GCATGGCCCTAAGTTGCTTGCTCGCCACGTCGATTGCTTTCGCTCTCCACCAAAAGAGTG
TGCTAAGAACTATTTTATCAGTGGGGAATTGACAAGCAGTTGCTATATGAATCCCGTAAT
ACAGGACCAGTGCGACTCTGAATGGATGATCTCCGGCTGCGGATTTAGCGCATACCCAGC
GTGGGCATAGTACCAAACACGCCCCGTGGTGTGTTGGCGCTTACACGGCTCGGCATGTGA
CATTTAGGGGCTATTGCGGTGACTAGACCTTCGCGGG
>Rosalind_1404
CACCTGACTGGCGATGCAAATCCAGCGTCTGTAGATAATATACCCTTTATAAAGGACCCG
ACCTAATTTTACTTCACCGCGACAGACCTTGTCTTCACTTTACTGGTCGCATGATGCGCA
ACATATGTCAAGTTAATTTAAGATCGCGCGTTGTCCATTCGATCGCGGAGAGACCGGATG
AGAGTTGATGCGTCGATAGCGTACCCGATAATCATCGTTAGTGCTCTGTCAGCGTCATGG
TTGTGTCTCCCACACGAGGTCTTAGCGCTCCCAAAGGTCGCGGACATACGAGACCGACGT
GGACGTTCTAACGAGCTCGTGTCGATGCACCCTCGAAATCTTAGAGTACGTAGTCCAATG
GTCCCTTTTGGTCGGACTTTACCGTCGATATGATTTCCCTGGCTGCGTCGCGAGGACGCA
GAAACGATCCAGCGTATATTGTAGGTCTTGTCCATTTTCTCGTCCGCGCAGACGAGGGTC
TCATGTATGGGGCGAGCTCTCATTTCAGCCGAAAAATAACTACCGAGGAAAAAGAGTATG
CTGTCTCGTAGTACTGCCTTGAGATGAGAGCCTTACAACGCAGCTGGATATAGTCTCGAG
GCGCGGAACTTCTGTACCCTGTCGTTAACTATGGTATGGGGAGTCCGATTTCTACTCATA
GGTGATGTGCCGCAGGCCTTGATCCATCGATTGGTCGATGTTTGGAGTTACCGCAAGAGA
ACACTAGACGTCGAGCAACCTAGCATCATCAAATCATCTGGCCTTCTATTGAAGTAGCGC
TTCAACCAGCAGTTAGAACCCCGCTCCATCTTTTTATATTACTCGAGCGACCAACGTCTA
TAGCAGGCGCGCGCCTGCCCCAGGCGCTTATTTAAAATTAATTCATCTGTTATGTTACCG
AAAACGATCACCCAGTCCACCAGCGTTTTGCCAGGAAGATCGAGAC
>Rosalind_5332
GTAACTTACTCACGCCAACTCTACCACTTCGGTGCCAAGGATGGTCATGTAGACGAATCC
GACTACAAATGCCGGGGCCGAGCACTAGTAGGCCAATATAAATTGAGCACCTCGCCCTAT
AAGGAGTCGCCTGCGGTGTAGTCCTAAAATAGAATCCACTGGTCCACGCAGGGAGTCGAC
TCCATCCGACTGTGTATGGCAACACTAGATGGCGTATAGCAAGTTGAATAATGCTATACT
CGGAGTTCTAGACTTATGGATGCATTGCGAGCTAGACTCACCTTGTAGTCAGTAAATTCG
ACCTAGAGCAATAGTAGTAATACCGGAGTAACAGTTTATCCAGCCTGGAGCGCGTCCGGG
GTTATCTCTCCACGCTTCGTTACCTTCGCTGTTCGAGCCGTACCATACATTGGAGGACCG
AAGCGTATCCAGCATCTAGGGCAGCGCGCTACGCGTTGCGTATGCCGATTACTTAGCAAT
TTAGCGCGTCCAGATATGACGTGGGTTATTTGGCGTTCGTGGCCTACAGTGCCATTCAGC
AAAAATTAACCTTCAGTAACCGAAACCTCGCACGATCCTGGTTAATTATATAGGTAGATT
TGAGGCTGGCCTATTACTAATTCATGTGAAGCGCTAGAAGAACGGCTGGATATGCGAGCA
TGGGGTTACCGTGTTAATCGAAATAAACTTGCGACATGGATAATGTCGGGGACTATACTA
GAGGGCTGGTTTACTAGGAAGGGGTGCCGGGCGAATATTCAGCGACAACGACCCGTATCT
ATCTAGAAAAACATGGCCAAAGCACATCCTAACAAACGTGTCTAC
")

(print-highest-gc ds)

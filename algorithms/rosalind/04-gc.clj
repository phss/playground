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

(def ds 
">Rosalind_6404
CCTGCGGAAGATCGGCACTAGAATAGCCAGAACCGTTTCTCTGAGGCTTCCGGCCTTCCC
TCCCACTAATAATTCTGAGG
>Rosalind_5959
CCATCGGTAGCGCATCCTTAGTCCAATTAAGTCCCTATCCAGGCGCTCCGCCGAAGGTCT
ATATCCATTTGTCAGCAGACACGC
>Rosalind_0808
CCACCCTCGTGGTATGGCTAGGCATTCAGGAACCGGAGAACGCTTCAGACCAGCCCGGAC
TGGGAACCTGCGGGCAGTAGGTGGAAT")

(print-highest-gc ds)

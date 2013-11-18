(ns dna
  (:use [clojure.string :only [replace]]))

(defn to-rna [dna]
  (let [thymidine "T" uracil "U"]
    (replace dna thymidine uracil)))

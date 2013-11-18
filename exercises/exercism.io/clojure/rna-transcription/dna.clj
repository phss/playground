(ns dna
  (:use [clojure.string :only [replace]]))

(defn to-rna [dna]
  (replace dna #"T" "U"))

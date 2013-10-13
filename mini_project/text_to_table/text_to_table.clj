; File stuff

(defn file-to-lines [filename]
  (let [contents (slurp filename)]
    (clojure.string/split-lines contents)))

(defn remove-blank-lines [lines]
  (remove clojure.string/blank? lines))

; 'Main'

(def filename "data/sample.txt")

(def text (remove-blank-lines (file-to-lines filename)))

(println text)

; File stuff

(defn file-to-lines [filename]
  (let [contents (slurp filename)]
    (clojure.string/split-lines contents)))

(defn remove-blank-lines [lines]
  (remove clojure.string/blank? lines))

; Parse

(defn parse-patterns [category patterns-text]
  (let [break #" - "
        flat-patterns ]))

(defn parse-text [text]
  (let [header? #(= \# (first %))
        flat-categories (partition-by header? text)
        grouped-categories (apply hash-map flat-categories)] 
    (flatten
      (for [[category patterns] grouped-categories]
        (parse-patterns (first category) patterns)))))

; 'Main'

(def filename "data/sample.txt")

(def text (remove-blank-lines (file-to-lines filename)))

(println (parse-text text))

; File stuff

(defn file-to-lines [filename]
  (let [contents (slurp filename)]
    (clojure.string/split-lines contents)))

(defn remove-blank-lines [lines]
  (remove clojure.string/blank? lines))

; Parse

(defn break-by [pattern strings]
  (let [flat (partition-by #(re-find pattern %) strings)
        grouped (apply hash-map flat)] 
    (into {}
      (for [[group-list group-strings] grouped]
        [(first group-list) group-strings]))))

(defn parse-patterns [[category patterns-text]]
  (let [title-pattern #" - "
        patterns (break-by title-pattern patterns-text)]
    (for [[title text] patterns
          :let [[pattern question] (clojure.string/split title title-pattern)]]
      {:category category, :pattern pattern, :question question, :answer text})))

(defn parse-text [text]
  (let [categories (break-by #"^#" text)] 
    (mapcat parse-patterns categories)))

; 'Main'

(def filename "data/sample.txt")

(def text (remove-blank-lines (file-to-lines filename)))

(println (parse-text text))

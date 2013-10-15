(ns ttt.core
  (:use [hiccup.core]))

; File stuff

(defn file-to-lines [filename]
  (let [contents (slurp filename)]
    (clojure.string/split-lines contents)))

(defn remove-blank-lines [lines]
  (remove clojure.string/blank? lines))

(defn text-from [filename]
  (remove-blank-lines (file-to-lines filename)))

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

(defn patterns-from [text]
  (let [categories (break-by #"^#" text)] 
    (mapcat parse-patterns categories)))

; Output

(defn header [s]
  (let [style {:style "font-weight:bold;"}]
    [:td style s]))

(defn shorten [text]
  (let [limit (.indexOf text ".")]
    (subs text 0 limit)))

(defn table-from [patterns]
  [:table
    [:tr  (header "Pattern") (header "Question") (header "Answer")]
    (for [{pattern :pattern question :question answer :answer} patterns]
      [:tr [:td pattern] [:td question] [:td (shorten (first answer))]])])

(defn make-html [patterns]
  (html [:html [:body 
    (for [[category patterns] (group-by :category patterns)]
      [:div [:h1 category] (table-from patterns)])]]))



(defn -main
  [filename & args]
  (let [text (text-from filename)
        patterns (patterns-from text)]
    (println (make-html patterns))))

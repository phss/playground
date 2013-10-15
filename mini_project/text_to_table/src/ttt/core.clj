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

(defn headers [col]
  (let [style {:style "font-weight:bold; font-size:1.2em;"}
        header (fn [s] [:td style s])]
    [:tr (map header col)]))

(defn bold-cell [s]
  (let [style {:style "font-weight:bold;"}]
    [:td style s]))

(defn italics-cell [s]
  (let [style {:style "font-style:italic;"}]
    [:td style s]))

(defn coll-size [size]
  [:col {:width size}])

(defn shorten [text]
  (let [limit (inc (.indexOf text "."))]
    (subs text 0 limit)))

(defn table-from [patterns]
  [:table {:style "border-collapse:separate; border-spacing:0 1.2em;"}
    (coll-size "150px") (coll-size "400px") (coll-size "400px")
    (headers ["Pattern" "Question" "Answer"])
    (for [{pattern :pattern question :question answer :answer} patterns]
      [:tr (bold-cell pattern) (italics-cell question) [:td (shorten (first answer))]])])

(defn make-html [patterns]
  (html [:html [:body {:style "font-family:\"nta\",Arial,sans-serif;"} 
    (for [[category patterns] (group-by :category patterns)]
      [:div [:h1 category] (table-from patterns)])]]))



(defn -main
  [filename & args]
  (let [text (text-from filename)
        patterns (patterns-from text)]
    (println (make-html patterns))))

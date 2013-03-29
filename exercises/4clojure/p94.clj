(ns fourclojure.p94
  (:require clojure.string))

(defn gol [board]
  (let [adj (fn [i j] (map (fn [[x y]] [(+ x i) (+ y j)]) [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]]))
        neighbours (fn [i j] (count (filter (fn [[x y]] 
                                              (= \# (nth (nth board x nil) y nil))) 
                                            (adj i j))))] 
    (map-indexed 
	    (fn [i rows]
	      (clojure.string/join ""
	        (map-indexed 
		        (fn [j elem] 
		          (if (or (and (= elem \#) (< 1 (neighbours i j) 4))
                      (and (= elem \ ) (= 3 (neighbours i j))))
                "#"
                " ")) 
		        rows))) 
	    board)))

(gol   ["      " 
        " ##   "
        " ##   "
        "   ## "
        "   ## "
        "      "])
(ns four-clojure.p15-test
  (:require [clojure.test :refer :all]
            [four-clojure.p15 :as p15]  
            [four-clojure.p16 :as p16]))

(deftest p15
  (is (= (p15/solution 7) 14)) 
  (is (= (p15/solution 3) 6)) 
  (is (= (p15/solution 11) 22)) 
  (is (= (p15/solution 2) 4)))

(deftest p16
  (is (= (p16/solution "Jenn") "Hello, Jenn!")) 
  (is (= (p16/solution "Dave") "Hello, Dave!")))

(ns four-clojure.p15-test
  (:require [clojure.test :refer :all]
            [four-clojure.p15 :refer :all]))

(deftest p15
  (is (= (solution 7) 14)) 
  (is (= (solution 3) 6)) 
  (is (= (solution 11) 22)) 
  (is (= (solution 2) 4)))

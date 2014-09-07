(ns four-clojure.p15-test
  (:require [clojure.test :refer :all]
            [four-clojure.p15 :as p15]))

(deftest p15
  (is (= (p15/solution 7) 14)) 
  (is (= (p15/solution 3) 6)) 
  (is (= (p15/solution 11) 22)) 
  (is (= (p15/solution 2) 4)))

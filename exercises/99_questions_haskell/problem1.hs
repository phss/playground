module Problem1 where

import Test.HUnit

myLast :: [a] -> a
myLast [] = error "empty list"
myLast (x:[]) = x
myLast (_:xs) = myLast xs

myLast' :: [a] -> a
myLast' = head . reverse

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "integer array" (myLast [1, 2, 3, 4]) 4,
  TestCase $ assertEqual "string array" (myLast "xyz") 'z']

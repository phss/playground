module Problem2 where

import Test.HUnit

myButLast :: [a] -> a
myButLast [] = error "empty list"
myButLast [_] = error "only one item"
myButLast [x, _] = x
myButLast (_:xs) = myButLast xs

myButLast' :: [a] -> a
myButLast' = head . reverse . init


main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "integer array" 3 (myButLast [1, 2, 3, 4]),
  TestCase $ assertEqual "string array" 'y' (myButLast ['a'..'z'])]

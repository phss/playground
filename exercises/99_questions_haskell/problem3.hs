module Problem3 where

import Test.HUnit

elementAt :: [a] -> Int -> a
elementAt [] _ = error "empty list"
elementAt (x:_) 1 = x
elementAt (_:xs) i = elementAt xs (i - 1)


main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "integer array" 2 (elementAt [1, 2, 3, 4] 2),
  TestCase $ assertEqual "string array" 'e' (elementAt "haskell" 5)]

module Problem5 where

import Test.HUnit

myReverse :: [a] -> [a]
myReverse [] = []
myReverse (x:xs) = myReverse xs ++ [x]

myReverse' :: [a] -> [a]
myReverse' = foldl (flip (:)) []


main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "string" "!amanap ,lanac a ,nalp a ,nam A" (myReverse "A man, a plan, a canal, panama!"),
  TestCase $ assertEqual "integer" [4,3,2,1] (myReverse [1,2,3,4])]

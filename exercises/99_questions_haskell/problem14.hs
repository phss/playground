module Problem14 where

import Test.HUnit

dupli :: [a] -> [a]
dupli [] = []
dupli (x:xs) = x:x:dupli xs

dupli' :: [a] -> [a]
dupli' = concatMap (replicate 2)

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "testing" [1,1,2,2,3,3]
                                   (dupli' [1,2,3])]

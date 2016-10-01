module Problem15 where

import Test.HUnit

repli :: [a] -> Int -> [a]
repli [] _ = []
repli (x:xs) n = replicate n x ++ repli xs n

repli' :: [a] -> Int -> [a]
repli' xs n = concatMap (replicate n) xs

repli'' :: [a] -> Int -> [a]
repli'' xs n = xs >>= replicate n

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "testing" [1,1,1,2,2,2,3,3,3]
                                   (repli'' [1,2,3] 3)]

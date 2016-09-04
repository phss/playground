module Problem9 where

import Test.HUnit
import Data.List (group)

pack :: (Eq a) => [a] -> [[a]]
pack = group

pack' :: (Eq a) => [a] -> [[a]]
pack' [] = []
pack' [a] = [[a]]
pack' (x:xs)
  | x == head y = (x:y):ys
  | otherwise   = [x]:rest
  where rest@(y:ys) = pack xs

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "something" ["aaaa","b","cc","aa","d","eeee"] (pack ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'])]

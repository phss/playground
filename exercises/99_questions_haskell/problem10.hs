module Problem10 where

import Test.HUnit
import Data.List (group)

encode :: (Eq a) => [a] -> [(Int, a)]
encode = map (\x -> (length x, head x)) . group

encode' :: (Eq a) => [a] -> [(Int, a)]
encode' [] = []
encode' [a] = [(1, a)]
encode' (x:xs)
  | x == y    = (n+1, y):ys
  | otherwise = (1, x):rest
  where rest@((n, y):ys) = encode xs

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "something" [(4,'a'),(1,'b'),(2,'c'),(2,'a'),(1,'d'),(4,'e')] (encode ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'])]

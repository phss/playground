module Problem11 where

import Test.HUnit
import Data.List (group)

data Encoding a = Single a | Multiple Int a deriving (Show, Eq)

encode :: (Eq a) => [a] -> [Encoding a]
encode = map (\x -> Multiple (length x) (head x)) . group

--encode' :: (Eq a) => [a] -> [(Int, a)]
--encode' [] = []
--encode' [a] = [(1, a)--]
--encode' (x:xs)
--  | x == y    = (n+1, y):ys
--  | otherwise = (1, x):rest
--  where rest@((n, y):ys) = encode xs

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "something" [Multiple 4 'a',Single 'b',Multiple 2 'c', Multiple 2 'a',Single 'd',Multiple 4 'e']
                                     (encode ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'])]

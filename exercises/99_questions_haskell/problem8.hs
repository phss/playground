module Problem8 where

import Test.HUnit
import Data.List (group)

compress :: (Eq a) => [a] -> [a]
compress = map head . group 

compress' :: (Eq a) => [a] -> [a]
compress' [] = []
compress' [x] = [x]
compress' (x:xs)
  | x == head rest = rest
  | otherwise      = x:rest
  where rest = compress' xs

compress'' :: (Eq a) => [a] -> [a]
compress'' = foldr addIfNew []
  where addIfNew x [] = [x]
        addIfNew x acc@(y:_) = if x == y then acc else x:acc

compress''' :: (Eq a) => [a] -> [a]
compress''' [] = []
compress''' (x:xs) = x:(compress . dropWhile (==x) $ xs)

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "something" "abacate" (compress "aaaabaaaaaaaccaateeee")]

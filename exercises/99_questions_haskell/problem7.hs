module Problem7 where

import Test.HUnit

data NestedList a = Elem a | List [NestedList a]

flatten :: NestedList a -> [a]
flatten (Elem x) = [x]
flatten (List x) = concat . map flatten $ x

flatten' :: NestedList a -> [a]
flatten' (Elem x) = [x]
flatten' (List []) = []
flatten' (List (x:xs)) = flatten x ++ flatten (List xs)

flatten'' :: NestedList a -> [a]
flatten'' (Elem x) = [x]
flatten'' (List xs) = foldr (\x acc -> flatten'' x ++ acc) [] xs

flatten''' :: NestedList a -> [a]
flatten''' (Elem x) = [x]
flatten''' (List xs) = foldr1 (++) . map flatten''' $ xs

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "single elem" [5] (flatten (Elem 5)),
  TestCase $ assertEqual "a few" [1,2,3,4,5] (flatten (List [Elem 1, List [Elem 2, List [Elem 3, Elem 4], Elem 5]]))]

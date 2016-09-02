module Problem6 where

import Test.HUnit

isPalindrome' :: (Eq a) => [a] -> Bool
isPalindrome' [] = True
isPalindrome' [_] = True
isPalindrome' (x:xs) = x == (last xs) && isPalindrome (init xs)

isPalindrome :: (Eq a) => [a] -> Bool
isPalindrome xs = first_half == reverse second_half
  where middle = length xs `div` 2
        (first_half, rest) = splitAt middle xs
        second_half = if even . length $ xs then rest else tail rest


main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "no pal" False (isPalindrome [1,2,3]),
  TestCase $ assertEqual "string pal" True (isPalindrome "madamimadam"),
  TestCase $ assertEqual "string pal" True (isPalindrome [1,2,4,8,16,8,4,2,1])]

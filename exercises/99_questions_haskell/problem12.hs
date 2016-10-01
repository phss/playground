module Problem12 where

import Test.HUnit
import Data.List (group)

data Encoding a = Single a | Multiple Int a deriving (Show, Eq)

decodeModified :: [Encoding a] -> [a]
decodeModified [] = []
decodeModified (Single x:xs) = x:decodeModified xs
decodeModified (Multiple n x:xs) = replicate n x ++ decodeModified xs

decodeModified' :: [Encoding a] -> [a]
decodeModified' = foldr (\x acc -> decode x ++ acc) []
  where decode (Single x) = [x]
        decode (Multiple n x) = replicate n x

main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "something" "aaaabccaadeeee"
                                     (decodeModified' [Multiple 4 'a',Single 'b',Multiple 2 'c', Multiple 2 'a',Single 'd',Multiple 4 'e'])]

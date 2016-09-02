module Problem4 where

import Test.HUnit

myLength :: [a] -> Int
myLength [] = 0
myLength (_:xs) = 1 + myLength xs

myLength' :: [a] -> Int
myLength' = sum . map (\_->1)



main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "length" 13 (myLength "Hello, world!")]

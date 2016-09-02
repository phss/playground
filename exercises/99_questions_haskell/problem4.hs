module Problem4 where

import Test.HUnit

myLength :: [a] -> Int
myLength [] = 0
myLength (_:xs) = 1 + myLength xs


main :: IO Counts
main = runTestTT $ TestList [
  TestCase $ assertEqual "length" 13 (myLength "Hello, world!")]

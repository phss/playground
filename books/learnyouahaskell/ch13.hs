module Book () where

import Data.Monoid
import Control.Monad.Writer

-- Writer
isBigGang :: Int -> (Bool, String)
isBigGang x = (x > 9, "Compared gang size to 9.")

applyLog :: (Monoid m) => (a, m) -> (a -> (b, m)) -> (b, m)
applyLog (x, log) f = let (y, newLog) = f x in (y, log `mappend` newLog)

type Food = String
type Price = Sum Int

addDrink :: Food -> (Food, Price)
addDrink "beans" = ("milk", Sum 25)
addDrink "jerky" = ("whiskey", Sum 99)
addDrink _ = ("beer", Sum 30)

logNumber :: Int -> Writer [String] Int
logNumber x = writer (x, ["Got number: " ++ show x])

multWithLog :: Writer [String] Int
multWithLog = do
  a <- logNumber 3
  b <- logNumber 5
  tell ["Gonna multiply these two"]
  return (a * b)

gcd' :: Int -> Int -> Writer [String] Int
gcd' a b
  | b == 0    = writer (a, ["Finished with " ++ show a])
  | otherwise = do
      tell [show a ++ " mod " ++ show b ++ " = " ++ show (a `mod` b)]
      gcd' b (a `mod` b)

-- Reader

addStuff :: Int -> Int
addStuff = do
  a <- (*2)
  b <- (+10)
  return (a + b)


-- State

type Stack = [Int]

pop :: Stack -> (Int, Stack)
pop (x:xs) = (x, xs)

push :: Int -> Stack -> ((), Stack)
push a xs = ((), a:xs)

stackManip :: Stack -> (Int, Stack)
stackManip stack = let
  ((),newStack1) = push 3 stack
  (a ,newStack2) = pop newStack1
  in pop newStack2

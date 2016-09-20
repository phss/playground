module Book () where

import Data.Monoid
import System.Random
import Control.Monad.Writer
import Control.Monad.State

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

pop :: State Stack Int
pop = state $ \(x:xs) -> (x, xs)

push :: Int -> State Stack ()
push a = state $ \xs -> ((), a:xs)

stackManip :: State Stack Int
stackManip = do
  push 3
  pop
  pop

stackStuff:: State Stack ()
stackStuff = do
  a <- pop
  if a == 5
    then push 5
    else do
      push 3
      push 8

moreStack :: State Stack ()
moreStack = do
  a <- stackManip
  if a == 100
    then stackStuff
    else return ()

stackyStack :: State Stack ()
stackyStack = do
  stackNow <- get
  if stackNow == [1,2,3]
    then put [8,3,1]
    else put [9,2,1]

randomSt :: (RandomGen g, Random a) => State g a
randomSt = state random

threeCoins :: State StdGen (Bool, Bool, Bool)
threeCoins = do
  a <- randomSt
  b <- randomSt
  c <- randomSt
  return (a, b, c)


-- Functions

keepSmall :: Int -> Writer [String] Bool
keepSmall x
    | x < 4 = do
        tell ["Keeping " ++ show x]
        return True
    | otherwise = do
        tell [show x ++ " is too large, throwing it away"]
        return False

powerset :: [a] -> [[a]]
powerset xs = filterM (\x -> [True, False]) xs

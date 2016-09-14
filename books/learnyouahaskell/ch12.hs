module Book () where

import Control.Monad

applyMaybe :: Maybe a -> (a -> Maybe b) -> Maybe b
applyMaybe Nothing _ = Nothing
applyMaybe (Just x) f = f x

type Birds = Int
type Pole = (Birds,Birds)

landLeft :: Birds -> Pole -> Maybe Pole
landLeft n (left,right)
  | abs (new_left - right) < 4 = Just (new_left, right)
  | otherwise                  = Nothing
  where new_left = left + n

landRight :: Birds -> Pole -> Maybe Pole
landRight n (left,right)
  | abs (left - new_right) < 4 = Just (left, new_right)
  | otherwise                  = Nothing
  where new_right = right + n

banana :: Pole -> Maybe Pole
banana _ = Nothing

foo :: Maybe String
foo = do
    x <- Just 3
    y <- Just "!"
    return (show x ++ y)


type KnightPos = (Int,Int)

moveKnight :: KnightPos -> [KnightPos]
moveKnight (c, r) = do
    (c',r') <- [(c+2,r-1),(c+2,r+1),(c-2,r-1),(c-2,r+1)
               ,(c+1,r-2),(c+1,r+2),(c-1,r-2),(c-1,r+2)
               ]
    guard (c' `elem` [1..8] && r' `elem` [1..8])
    return (c',r')

in3 :: KnightPos -> [KnightPos]
in3 start = return start >>= moveKnight >>= moveKnight >>= moveKnight

canReachIn3 :: KnightPos -> KnightPos -> Bool
canReachIn3 start end = end `elem` in3 start

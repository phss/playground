module Book
( Point(..)
, Shape(..)
, surface
, nudge
, baseCircle
, baseRect
) where

import qualified Data.Map as Map

-- Shape
data Point = Point Float Float deriving (Show)
data Shape = Circle Point Float | Rectangle Point Point deriving (Show)

surface :: Shape -> Float
surface (Circle _ r) = pi * r^2
surface (Rectangle (Point x1 y1) (Point x2 y2)) = (abs $ x2 - x1) * (abs $ y2 - y1)

nudge :: Shape -> Float -> Float -> Shape
nudge (Circle (Point x y) r) a b = Circle (Point (x + a) (y + b)) r
nudge (Rectangle (Point x1 y1) (Point x2 y2)) a b = Rectangle (Point (x1+a) (y1+b)) (Point (x2+a) (y2+b))

baseCircle :: Float -> Shape
baseCircle r = Circle (Point 0 0) r

baseRect :: Float -> Float -> Shape
baseRect w h = Rectangle (Point 0 0) (Point w h)

-- Person record
data Person = Person { firstName :: String
                     , lastName :: String
                     , age :: Int
                     , height :: Float
                     , phoneNumber :: String
                     , flavor :: String
                     } deriving (Show)

tellName :: Person -> String
tellName (Person {firstName = f, lastName = l}) = f ++ " " ++ l

-- Dervied class
data AnotherPerson = AnotherPerson { name :: String
                     , year :: Int
                     } deriving (Eq, Show)

data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
           deriving (Eq, Ord, Show, Read, Bounded, Enum)

weekStart :: Day
weekStart = minBound

weekEnd :: Day
weekEnd = maxBound

nextDay :: Day -> Day
nextDay = succ

prevDay :: Day -> Day
prevDay = pred

week :: [Day]
week = [Monday .. Friday]

weekend :: [Day]
weekend = [Saturday, Sunday]

-- Type synonyms
type ViewableTuple = (String, String)

printTuple :: ViewableTuple -> String
printTuple (a, b) = a ++ " " ++ b

data LockerState = Taken | Free deriving (Show, Eq)
type Code = String
type LockerMap = Map.Map Int (LockerState, Code)

lockerLookup :: Int -> LockerMap -> Either String Code
lockerLookup lockerNumber map =
    case Map.lookup lockerNumber map of
        Nothing -> Left $ "Locker number " ++ show lockerNumber ++ " doesn't exist!"
        Just (state, code) -> if state /= Taken
                                then Right code
                                else Left $ "Locker " ++ show lockerNumber ++ " is already taken!"

lockers :: LockerMap
lockers = Map.fromList
    [(100,(Taken,"ZD39I"))
    ,(101,(Free,"JAH3I"))
    ,(103,(Free,"IQSA9"))
    ,(105,(Free,"QOTSA"))
    ,(109,(Taken,"893JJ"))
    ,(110,(Taken,"99292"))
    ]

-- Recursive types

data MyList a = Empty | Cons { listHead :: a, listTail :: MyList a} deriving (Show, Read, Eq, Ord)

someList = 3 `Cons` (4 `Cons` (5 `Cons` Empty))

data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)

singleton :: a -> Tree a
singleton x = Node x EmptyTree EmptyTree

treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x EmptyTree = singleton x
treeInsert x (Node y left right)
  | x == y = Node x left right
  | x < y  = Node y (treeInsert x left) right
  | x > y  = Node y left (treeInsert x right)

treeElem :: (Ord a) => a -> Tree a -> Bool
treeElem _ EmptyTree = False
treeElem x (Node y left right)
  | x == y = True
  | x < y  = treeElem x right

-- Typeclass
data TrafficLight = Red | Yellow | Green

instance Eq TrafficLight where
  Red == Red = True
  Green == Green = True
  Yellow == Yellow = True
  _ == _ = False

instance Show TrafficLight where
    show Red = "Red light"
    show Yellow = "Yellow light"
    show Green = "Green light"

class YesNo a where
  yesno :: a -> Bool

instance YesNo Integer where
  yesno 0 = False
  yesno _ = True

instance YesNo [a] where
  yesno [] = False
  yesno _ = True

instance YesNo Bool where
  yesno = id

instance YesNo (Maybe a) where
  yesno (Just _) = True
  yesno Nothing = False

instance YesNo (Tree a) where
    yesno EmptyTree = False
    yesno _ = True

instance YesNo TrafficLight where
    yesno Red = False
    yesno _ = True

yesnoIf :: (YesNo y) => y -> a -> a -> a
yesnoIf yesnoVal yesResult noResult = if yesno yesnoVal then yesResult else noResult

-- Functor
instance Functor Tree where
  fmap f EmptyTree = EmptyTree
  fmap f (Node x leftTree rightTree) = Node (f x) (fmap f leftTree) (fmap f rightTree)

-- Foo
class Tofu t where
  tofu :: j a -> t a j

data Frank a b  = Frank {frankField :: b a} deriving (Show)

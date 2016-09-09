module Book () where

import Data.List

data Section = Section { getA :: Int, getB :: Int, getC :: Int } deriving (Show)
type RoadSystem = [Section]

heathrowToLondon :: RoadSystem
heathrowToLondon = [Section 50 10 30, Section 5 90 20, Section 40 2 25, Section 10 8 0]

data Label = A | B | C deriving (Show)
type Path = [(Label, Int)]

priceOf :: Path -> Int
priceOf = sum . map snd

roadStep :: (Path, Path) -> Section -> (Path, Path)
roadStep (pathA, pathB) (Section a b c) =
  let forwardPriceToA = priceOf pathA + a
      forwardPriceToB = priceOf pathB + b
      crossPriceToA = forwardPriceToB + c
      crossPriceToB = forwardPriceToA + c
      newPathToA = if forwardPriceToA <= crossPriceToA then (A, a):pathA else (C, c):(B, b):pathB
      newPathToB = if forwardPriceToB <= crossPriceToB then (B, b):pathB else (C, c):(A, a):pathA
  in (newPathToA, newPathToB)

optimalPath :: RoadSystem -> Path
optimalPath roadSystem =
  let (bestPathA, bestPathB) = foldl roadStep ([], []) roadSystem
      bestPath = if (sum (map snd bestPathA)) <= (sum (map snd bestPathB)) then bestPathA else bestPathB
  in reverse bestPath

groupsOf :: Int -> [a] -> [[a]]
groupsOf 0 _ = undefined
groupsOf _ [] = []
groupsOf n xs = take n xs : groupsOf n (drop n xs)

main = do
  contents <- getContents
  let threes = groupsOf 3 . map read $ lines contents
      roadSystem =  map (\[a,b,c] -> Section a b c) threes
      path = optimalPath roadSystem
      pathString = concat $ map (show . fst) path
      pathPrice = sum $ map snd path
  putStrLn $ "The best path to take is: " ++ pathString
  putStrLn $ "The price is: " ++ show pathPrice

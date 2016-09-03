module Book where

import Data.List
import Geometry

numUniques :: (Eq a) => [a] -> Int
numUniques = length . nub

myCubVolume = cubeVolume 42


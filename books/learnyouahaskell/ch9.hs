module Book () where

import System.Random

threeCoins :: StdGen -> (Bool, Bool, Bool)
threeCoins gen =
  let (firstCoin, newGen) = random gen
      (secondCoin, newGen') = random newGen
      (thirdCoin, _) = random newGen'
  in (firstCoin, secondCoin, thirdCoin)

diceRoll :: StdGen -> Int -> (Int, StdGen)
diceRoll gen diceSize = randomR (1, diceSize) gen

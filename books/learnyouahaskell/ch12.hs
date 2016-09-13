module Book () where

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

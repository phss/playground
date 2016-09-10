module Book () where

data CMaybe a = CNothing | CJust Int a deriving (Show)

instance Functor CMaybe where
    fmap f CNothing = CNothing
    fmap f (CJust counter x) = CJust (counter+1) (f x)

combs :: (a -> b -> c) -> [a] -> [b] -> [c]
combs f a b = f <$> a <*> b

newtype Pair b a = Pair { getPair :: (a,b) }
instance Functor (Pair c) where
    fmap f (Pair (x,y)) = Pair (f x, y)

newtype CoolBool = CoolBool { getCoolBool :: Bool }
helloMe :: CoolBool -> String
helloMe (CoolBool _) = "hello"

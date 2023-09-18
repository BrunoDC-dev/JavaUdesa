valorAbsoluto :: Float -> Float
valorAbsoluto x  | x >= 0  = x 
                 |otherwise = -x

bisiesto :: Int -> Bool
bisiesto x | mod x 400 == 0 = True
           | mod x 4 == 0 && mod x 100 /= 0 = True
           | otherwise = False

factorial :: Int -> Int
factorial 0 = 1
factorial x = x * factorial (x-1)

esPrimo :: Int -> Bool
esPrimo x = length [y | y <- [1..x], mod x y == 0] == 2

cantDivisoresPrimos :: Int -> Int
cantDivisoresPrimos x = length [y | y <- [1..x], mod x y == 0, esPrimo y]


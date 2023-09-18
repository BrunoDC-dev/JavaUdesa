module Tipos where

type Datos = String
type Nombre = String
type Etiqueta = String  
-- insertar 'tercera' ['ganamos', 'la'] -> ['ganamos', 'la', 'tercera']
insertar :: a -> [a] -> [a]
insertar x xs = xs ++ [x]
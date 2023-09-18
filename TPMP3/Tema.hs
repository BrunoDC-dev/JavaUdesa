
module Tema (Tema , nuevoT ,nombreT, datosT, etiquetasT, agregarT , aplicaT ) where
import Tipos

data Tema = Tem Nombre [Etiqueta] Datos  deriving (Show, Eq,Ord)

nuevoT :: Nombre ->Datos ->  Tema
nuevoT nombre datos = Tem nombre [] datos

nombreT :: Tema -> Nombre
nombreT (Tem nombre _ _) = nombre

datosT:: Tema -> Datos
datosT (Tem _ _ datos) = datos

etiquetasT:: Tema -> [Etiqueta]
etiquetasT (Tem _ etiquetas _) = etiquetas

agregarT :: Etiqueta -> Tema -> Tema
agregarT etiqueta tema@(Tem nombre etiquetas datos) | aplicaT tema etiqueta = error "La etiqueta ya existe"
                                                | otherwise = Tem nombre (etiquetas ++ [etiqueta]) datos
aplicaT :: Tema -> Etiqueta -> Bool
aplicaT (Tem _ etiquetas _) etiqueta = elem etiqueta etiquetas



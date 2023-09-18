module Reproductor(Reproductor (RP), nuevoR , archivosR ,listaParaR , temasR , playR , actualR , avanzarR , retrocederR, reiniciarR) where

import Tipos 
import Tema
import Playlist
import FileSystem 

data Reproductor = RP FileSystem Playlist  deriving (Show, Eq)

nuevoR :: FileSystem -> Reproductor
nuevoR fs = RP fs (nuevaP [])

archivosR :: Reproductor -> FileSystem
archivosR (RP fs _) = fs

listaParaR :: Etiqueta -> Reproductor -> [Tema]
listaParaR etiqueta (RP fs pl) = filtarF etiqueta fs

temasR :: Reproductor -> [Tema]
temasR (RP fs pl) = temasF fs

playR :: Reproductor -> Etiqueta -> Reproductor
playR reproductor@(RP fs pl) etiqueta = ( RP fs playlist )
    where
        playlist = nuevaP (listaParaR etiqueta reproductor)

actualR :: Reproductor -> Tema
actualR (RP _ pl) = actualP pl

avanzarR :: Reproductor -> Reproductor
avanzarR (RP fs pl) = RP fs (skipP pl)

retrocederR :: Reproductor -> Reproductor
retrocederR (RP fs pl) = RP fs (backP pl)

reiniciarR :: Reproductor -> Reproductor
reiniciarR (RP fs pl) = RP fs (resetP pl)
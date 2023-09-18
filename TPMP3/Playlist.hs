module Playlist (Playlist(Play), nuevaP, actualP, skipP, backP, resetP ) where

import Tipos
import Tema


data Playlist = Play Int [Tema ] deriving (Eq,Show)

nuevaP :: [Tema] -> Playlist
nuevaP temas = Play 0 temas

actualP :: Playlist -> Tema
actualP (Play i temas) = temas !! i

skipP :: Playlist -> Playlist
skipP (Play i temas) | i == (length temas -1) = Play 0 temas
                     | otherwise = Play (i+1) temas

backP :: Playlist -> Playlist
backP (Play i temas) | i == 0 = Play (length temas -1) temas
                     | otherwise = Play (i-1) temas

resetP  :: Playlist -> Playlist
resetP (Play i temas) = Play 0 temas

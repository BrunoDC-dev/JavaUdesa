import Tipos
import Tema
import Playlist
-- Pruebas para nuevoT
pruebaNuevoT :: Bool
pruebaNuevoT = nombreT tema == "Tema1" && datosT tema == "Datos1"
  where
    tema = nuevoT "Tema1" "Datos1"

-- Pruebas para agregarT
pruebaAgregarT :: Bool
pruebaAgregarT = etiquetasT tema == ["Etiqueta1"]
  where
    tema = agregarT "Etiqueta1" (nuevoT "Tema1" "Datos1")

-- Pruebas para aplicaT
pruebaAplicaT :: Bool
pruebaAplicaT = aplicaT tema "Etiqueta1" && not (aplicaT tema "Etiqueta2")
  where
    tema = agregarT "Etiqueta1" (nuevoT "Tema1" "Datos1")

pruebasDatsoT :: Bool
pruebasDatsoT = datosT tema == "Datos1"
  where
    tema = nuevoT "Tema1" "Datos1"


-- Pruebas para skipP
pruebaSkipP :: Bool
pruebaSkipP = resultado == esperado && resultadoExtrmo == esperadoExtrmo
  where
    temas = [nuevoT "Tema1" "Datos1", nuevoT "Tema2" "Datos2", nuevoT "Tema3" "Datos3"]
    resultado = actualP (skipP (Play 1 temas))
    esperado = temas !! 2
    resultadoExtrmo = actualP (skipP (Play 2 temas))
    esperadoExtrmo = head temas

-- Pruebas para backP
pruebaBackP :: Bool
pruebaBackP = resultado == esperado && resultadoExtrmo == esperadoExtrmo
  where
    temas = [nuevoT "Tema1" "Datos1", nuevoT "Tema2" "Datos2", nuevoT "Tema3" "Datos3"]
    resultado = actualP (backP (Play 1 temas))
    esperado = head temas
    resultadoExtrmo = actualP (backP (Play 0 temas))
    esperadoExtrmo = temas !! 2

-- Pruebas para resetP
pruebaResetP :: Bool
pruebaResetP = resultado == esperado
  where
    temas = [nuevoT "Tema1" "Datos1", nuevoT "Tema2" "Datos2", nuevoT "Tema3" "Datos3"]
    resultado = actualP (resetP (Play 2 temas))
    esperado = head temas

-- Lista de todas las pruebas
pruebasTema :: [Bool]
pruebasTema = [pruebaNuevoT, pruebaAgregarT, pruebaAplicaT, pruebasDatsoT, pruebaSkipP, pruebaBackP, pruebaResetP]

-- Función para ejecutar todas las pruebas y mostrar resultados
main :: IO ()
main = do
  let resultados = map (\(i, resultado) -> "Prueba " ++ show i ++ ": " ++ if resultado then "PASSED" else "FAILED") (zip [1..] pruebasTema)
  mapM_ putStrLn resultados

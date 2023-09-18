import Tipos
import Tema

-- Pruebas para nuevoT
pruebaNuevoT :: Bool
pruebaNuevoT = nombreT tema == "Tema1" && datosT tema == "Datos1"
  where
    tema = nuevoT "Tema1" "Datos1"

-- Pruebas para agregarT
pruebaAgregarT :: Bool
pruebaAgregarT = etiquetasT tema == ["Etiqueta1"] && not (aplicaT tema "Etiqueta1")
  where
    tema = agregarT "Etiqueta1" (nuevoT "Tema1" "Datos1")

-- Pruebas para aplicaT
pruebaAplicaT :: Bool
pruebaAplicaT = aplicaT tema "Etiqueta1" && not (aplicaT tema "Etiqueta2")
  where
    tema = agregarT "Etiqueta1" (nuevoT "Tema1" "Datos1")

-- Lista de todas las pruebas
pruebasTema :: [Bool]
pruebasTema = [pruebaNuevoT, pruebaAgregarT, pruebaAplicaT]

-- Función para ejecutar todas las pruebas y mostrar resultados
main :: IO ()
main = do
  let resultados = map (\(i, resultado) -> "Prueba " ++ show i ++ ": " ++ if resultado then "PASSED" else "FAILED") (zip [1..] pruebasTema)
  mapM_ putStrLn resultados

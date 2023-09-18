    module FileSystem (FileSystem , nuevoF, etiquetasF, temasF, agregarF, filtarF) where
    import Tipos
    import Tema

    data FileSystem = FS [Etiqueta] [Tema] deriving (Eq,Show)

    nuevoF :: FileSystem
    nuevoF = FS [] []

    etiquetasF:: FileSystem -> [Etiqueta]
    etiquetasF (FS etiquetas _) = etiquetas

    temasF :: FileSystem -> [Tema]
    temasF (FS _ temas) = temas

    agregarF :: Tema -> FileSystem -> FileSystem
    agregarF tema (FS etiquetas temas) = FS (etiquetas ++ etiquetasT tema) (temas ++ [tema])

    filtarF :: Etiqueta -> FileSystem -> [Tema]
    filtarF etiqueta (FS _ temas) = filter (\tema -> aplicaT tema etiqueta) temas

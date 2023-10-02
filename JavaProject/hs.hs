import Data.List (map)

data Drug = Drug { name :: String }

drugs :: [Drug]
drugs = [Drug "Drug1", Drug "Drug2", Drug "Drug3"]

candidateNames :: [String]
candidateNames = map name drugs
module Main where

import System.Environment (getArgs)
import Lex (alexScanTokens)
import Parser (parseProgram)
import Token (Programa(..))

main :: IO ()
main = do
    args <- getArgs
    case args of
        [inputFile] -> do
            source <- readFile inputFile
            let tokens = alexScanTokens source
            case parseProgram tokens of
                Left err -> putStrLn $ "Erro de parsing: " ++ err
                Right ast -> do
                    putStrLn "Análise sintática concluída com sucesso!"
                    putStrLn "\nÁrvore Sintática Abstrata (AST):"
                    print ast
        _ -> putStrLn "Uso: compilador <arquivo_de_entrada>"
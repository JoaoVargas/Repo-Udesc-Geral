{ 
module Lex where

import Token (Token(..)) 
}

%wrapper "basic"

$digit = [0-9]
$alpha = [a-zA-Z]
$idchar = [a-zA-Z0-9_]

@int     = $digit+
@float   = $digit+\.$digit+ 
@string  = \"[^\"]*\"
@id      = [$alpha][$idchar]*

tokens :-

<0> $white+ ;                  -- Ignora espaços
<0> "//".* ;                   -- Ignora comentários

-- Palavras-chave
<0> "int"      {\s -> TokInt}
<0> "float"    {\s -> TokFloat}
<0> "string"   {\s -> TokString}
<0> "void"     {\s -> TokVoid}
<0> "if"       {\s -> TokIf}
<0> "else"     {\s -> TokElse}
<0> "while"    {\s -> TokWhile}
<0> "return"   {\s -> TokReturn}
<0> "print"    {\s -> TokPrint}
<0> "read"     {\s -> TokRead}

-- Literais
<0> @int       {\s -> TokIntLit (read s)}
<0> @float     {\s -> TokFloatLit (read s)}
<0> @string    {\s -> TokStringLit (init (tail s))}  -- Remove aspas

-- Identificadores
<0> @id        {\s -> TokId s}

-- Operadores e pontuação
<0> "="        {\s -> TokAssign}
<0> "=="       {\s -> TokEQ}
<0> "!="       {\s -> TokNEQ}
<0> "<"        {\s -> TokLT}
<0> ">"        {\s -> TokGT}
<0> "<="       {\s -> TokLE}
<0> ">="       {\s -> TokGE}
<0> "&&"       {\s -> TokAnd}
<0> "||"       {\s -> TokOr}
<0> "!"        {\s -> TokNot}
<0> "+"        {\s -> TokPlus}
<0> "-"        {\s -> TokMinus}
<0> "*"        {\s -> TokTimes}
<0> "/"        {\s -> TokDivide}
<0> "("        {\s -> TokLParen}
<0> ")"        {\s -> TokRParen}
<0> "{"        {\s -> TokLBrace}
<0> "}"        {\s -> TokRBrace}
<0> ";"        {\s -> TokSemicolon}
<0> ","        {\s -> TokComma}

{
testLex = do
  s <- getLine
  print (alexScanTokens s)
}
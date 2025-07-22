{
module Lex where

import Token
}

%wrapper "basic"

$digit = [0-9]          -- digits
@num = $digit+(\.$digit+)?

tokens :-

<0> $white+ ;
<0> @num {\s -> NUM (read s)}
<0> "+" {\s -> ADD}
<0> "-" {\s -> SUB}  
<0> "*" {\s -> MUL}  
<0> "/" {\s -> DIV}  
<0> "(" {\s -> LPAR}  
<0> ")" {\s -> RPAR}
<0> "&&" {\s -> AND}
<0> "||" {\s -> OR}
<0> "!" {\s -> NOT}
<0> "==" {\s -> EQUAL}
<0> "/=" {\s -> NOT_EQUAL}
<0> ">=" {\s -> GREATER_THAN_EQUAL}
<0> "<=" {\s -> LESS_THAN_EQUAL}
<0> ">" {\s -> GREATER_THAN}
<0> "<" {\s -> LESS_THAN}


{
-- As acoes tem tipo :: String -> Token

testLex = do s <- getLine
             print (alexScanTokens s)
}
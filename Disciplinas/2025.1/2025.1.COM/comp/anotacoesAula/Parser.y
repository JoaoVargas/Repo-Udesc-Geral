{
module Parser where

import Token
import qualified Lex as L
data Expr = Add Expr Expr | Sub Expr Expr | Mul Expr Expr | Div Expr Expr | Num Double deriving Show
}


%name calc
%tokentype { Token }
%error { parseError }
%token 
  '+' {ADD}
  '-' {SUB}
  '*' {MUL}
  '/' {DIV}
  '(' {LPAR}
  ')' {RPAR}
  Num {NUM $$}


%%
ExprR : Expr '>' Expr       {$1 > $ $3}

Expr  : Expr '+' Term       {Add $1 + $3}
      | Expr '-' Term       {Sub$1 - $3}
      | Term                {$1}

Term  : Term  '*' Factor    {Mul $1 * $3}
      | Term '/' Factor     {Div $1 / $3}
      | Factor              {$1}

Factor : Num                {Num $1}
       | '(' Expr ')'       {$2}      


{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

main = do putStr "Expressão:"
          s <- getLine
          print (calc (L.alexScanTokens s))
}
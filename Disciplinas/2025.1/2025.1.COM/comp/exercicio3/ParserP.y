{
module Parser where

import Token
import qualified Lex as L

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
  '&&' {AND}
  '||' {OR}
  '!' {NOT}
  '==' {EQUAL}
  '/=' {NOT_EQUAL}
  '>=' {GREATER_THAN_EQUAL}
  '<=' {LESS_THAN_EQUAL}
  '>' {GREATER_THAN}
  '<' {LESS_THAN}
  Num {NUM $$}

%%

Expr 
  : ExprLog {Left $1}
  | ExprAri {Right $1}

ExprAri 
  : ExprAri '+' TermAri {$1 + $3}
  | ExprAri '-' TermAri {$1 - $3}
  | TermAri {$1}

TermAri 
  : TermAri '*' FactorAri {$1 * $3}
  | TermAri '/' FactorAri {$1 / $3}
  | FactorAri {$1}

FactorAri 
  : Num {$1}
  | '(' ExprAri ')'{$2}

ExprRel
  : ExprAri '>' ExprAri {$1 > $3}
  | ExprAri '<' ExprAri {$1 < $3}
  | ExprAri'>=' ExprAri {$1 >= $3}
  | ExprAri'<=' ExprAri {$1 <= $3}
  | ExprAri'==' ExprAri {$1 == $3}
  | ExprAri'/=' ExprAri {$1 /= $3}

ExprLog
  : ExprLog '||' TermLog {$1 || $3}
  | ExprLog '&&' TermLog {$1 && $3}
  | TermLog {$1}

TermLog
  : '!' TermLog {not $2}
  | '(' ExprLog ')' {$2}
  | ExprRel {$1}

{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

main = do putStr "Expressão:"
          s <- getLine
          print (calc (L.alexScanTokens s))
}
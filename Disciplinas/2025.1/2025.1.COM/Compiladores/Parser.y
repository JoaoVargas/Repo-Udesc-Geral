{ 
module Parser where

import Token
import Lex
}

%name parseProgram
%tokentype { Token }
%monad { Either String } { (>>=) } { return }
%error { parseError }

%token
  TokInt        { TokInt }
  TokFloat      { TokFloat }
  TokString     { TokString }
  TokVoid       { TokVoid }
  TokIf         { TokIf }
  TokElse       { TokElse }
  TokWhile      { TokWhile }
  TokReturn     { TokReturn }
  TokPrint      { TokPrint }
  TokRead       { TokRead }
  TokAssign     { TokAssign }
  TokEQ         { TokEQ }
  TokNEQ        { TokNEQ }
  TokLT         { TokLT }
  TokGT         { TokGT }
  TokLE         { TokLE }
  TokGE         { TokGE }
  TokAnd        { TokAnd }
  TokOr         { TokOr }
  TokNot        { TokNot }
  TokPlus       { TokPlus }
  TokMinus      { TokMinus }
  TokTimes      { TokTimes }
  TokDivide     { TokDivide }
  TokLParen     { TokLParen }
  TokRParen     { TokRParen }
  TokLBrace     { TokLBrace }
  TokRBrace     { TokRBrace }
  TokSemicolon  { TokSemicolon }
  TokComma      { TokComma }
  TokIntLit     { TokIntLit $$ }
  TokFloatLit   { TokFloatLit $$ }
  TokStringLit  { TokStringLit $$ }
  TokId         { TokId $$ }

%right TokNot
%left TokOr
%left TokAnd
%nonassoc TokEQ TokNEQ TokLT TokGT TokLE TokGE
%left TokPlus TokMinus
%left TokTimes TokDivide

%% 

Programa : ListaFuncoes BlocoPrincipal    { Prog $1 (map extractFuncDef $1) [] $2 }
         | BlocoPrincipal                 { Prog [] [] [] $1 }

ListaFuncoes : ListaFuncoes Funcao        { $1 ++ [$2] }
             | Funcao                     { [$1] }

Funcao : TipoRetorno TokId TokLParen Parametros TokRParen BlocoPrincipal 
         { ($2, $4, $1, $6) }
       | TipoRetorno TokId TokLParen TokRParen BlocoPrincipal 
         { ($2, [], $1, $5) }

TipoRetorno : Tipo    { $1 }
            | TokVoid  { TVoid }

Parametros : DeclParametros   { $1 }

DeclParametros : DeclParametros TokComma Parametro   { $1 ++ [$3] }
               | Parametro                        { [$1] }

Parametro : Tipo TokId   { ($2, $1) }

BlocoPrincipal : TokLBrace Declaracoes ListaCmd TokRBrace   { $2 ++ $3 }
               | TokLBrace ListaCmd TokRBrace               { $2 }

Declaracoes : Declaracoes Declaracao   { $1 ++ [$2] }
            | Declaracao               { [$1] }

Declaracao : Tipo ListaId TokSemicolon   { Decl $1 $2 }

Tipo : TokInt     { TInt }
     | TokFloat   { TDouble }
     | TokString  { TString }

ListaId : ListaId TokComma TokId   { $1 ++ [$3] }
        | TokId                 { [$1] }

ListaCmd : ListaCmd Comando   { $1 ++ [$2] }
         | Comando            { [$1] }

Comando : CmdSe                     { $1 }
        | CmdEnquanto               { $1 }
        | CmdAtrib                  { $1 }
        | CmdEscrita                { $1 }
        | CmdLeitura                { $1 }
        | ChamadaProc               { $1 }
        | Retorno                   { $1 }

CmdSe : TokIf TokLParen ExprLogica TokRParen Bloco 
        { If $3 $5 [] }
      | TokIf TokLParen ExprLogica TokRParen Bloco TokElse Bloco 
        { If $3 $5 $7 }

CmdEnquanto : TokWhile TokLParen ExprLogica TokRParen Bloco 
             { While $3 $5 }

CmdAtrib : TokId TokAssign ExprAritmetica TokSemicolon 
          { Atrib $1 $3 }

CmdEscrita : TokPrint TokLParen ExprAritmetica TokRParen TokSemicolon 
            { Imp $3 }
          | TokPrint TokLParen TokStringLit TokRParen TokSemicolon 
            { Imp (Lit $3) }

CmdLeitura : TokRead TokLParen TokId TokRParen TokSemicolon 
            { Leitura $3 }

ChamadaProc : ChamadaFuncao TokSemicolon 
             { Proc $1 }

Retorno : TokReturn TokSemicolon              { Ret Nothing }
        | TokReturn ExprAritmetica TokSemicolon { Ret (Just $2) }
        | TokReturn TokStringLit TokSemicolon   { Ret (Just (Lit $3)) }

ChamadaFuncao : TokId TokLParen ParametrosChamada TokRPEN   { ($1, $3) }
              | TokId TokLParen TokRParen                     { ($1, []) }

ParametrosChamada : ListaExpr   { $1 }

ListaExpr : ListaExpr TokComma ExprAritmetica   { $1 ++ [$3] }
          | ExprAritmetica                   { [$1] }

ExprLogica : ExprLogica TokOr ExprLogica        { Or $1 $3 }
           | ExprLogica TokAnd ExprLogica      { And $1 $3 }
           | TokNot ExprLogica                 { Not $2 }
           | ExprRelacional                 { Rel $1 }
           | TokLParen ExprLogica TokRParen       { $2 }

ExprRelacional : ExprAritmetica TokEQ ExprAritmetica   { Req $1 $3 }
               | ExprAritmetica TokNEQ ExprAritmetica  { Rdiff $1 $3 }
               | ExprAritmetica TokLT ExprAritmetica   { Rlt $1 $3 }
               | ExprAritmetica TokGT ExprAritmetica   { Rgt $1 $3 }
               | ExprAritmetica TokLE ExprAritmetica   { Rle $1 $3 }
               | ExprAritmetica TokGE ExprAritmetica   { Rge $1 $3 }

ExprAritmetica : ExprAritmetica TokPlus Termo      { Add $1 $3 }
               | ExprAritmetica TokMinus Termo     { Sub $1 $3 }
               | Termo                          { $1 }

Termo : Termo TokTimes Fator     { Mul $1 $3 }
      | Termo TokDivide Fator    { Div $1 $3 }
      | Fator                 { $1 }

Fator : TokMinus Fator           { Neg $2 }
      | TokIntLit               { Const (CInt $1) }
      | TokFloatLit             { Const (CDouble $1) }
      | TokId                    { IdVar $1 }
      | ChamadaFuncao         { Chamada (fst $1) (snd $1) }
      | TokLParen ExprAritmetica TokRParen { $2 }

Bloco : TokLBrace ListaCmd TokRBrace { $2 }

{
parseError :: [Token] -> Either String a
parseError s = Left ("Parse error: " ++ show s)

extractFuncDef :: (String, [Var], Tipo, Bloco) -> (Id, [Var], Bloco)
extractFuncDef (fname, params, _, corpo) = (fname, params, corpo)
}
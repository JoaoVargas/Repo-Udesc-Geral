import Text.Parsec
import Text.Parsec.Expr
import qualified Text.Parsec.Token as T
import Text.Parsec.Language


type Id = String
data Tipo = TDouble | TInt | TString | TVoid deriving Show
data TCons = CDouble Double | CInt Integer deriving Show
data Expr = Expr :+: Expr | Expr :-: Expr | Expr :*: Expr | Expr :/: Expr | Neg Expr | Const TCons | IdVar Id | Chamada Id [Expr] | Lit String deriving Show
data ExprR = Expr :==: Expr | Expr :/=: Expr | Expr :<: Expr | Expr :>: Expr |Expr :<=: Expr | Expr :>=: Expr deriving Show
data ExprL = ExprL :&: ExprL | ExprL :|: ExprL | Not ExprL | Rel ExprR deriving Show
data Var = Id :#: Tipo deriving Show
data Funcao = Id :->: ([Var], Tipo) deriving Show
data Programa = Prog [Funcao] [(Id, [Var], Bloco)] [Var] Bloco deriving Show
type Bloco = [Comando]
data Comando = If ExprL Bloco Bloco
                | While ExprL Bloco
                | Atrib Id Expr
                | Leitura Id
                | Imp Expr
                | Ret (Maybe Expr)
                | Proc Id [Expr]
                | Print Expr
                | ReadAction String
                    deriving Show


definicao = emptyDef
  {
    T.commentStart    = "{-",
    T.commentEnd      = "-}",
    T.commentLine     = "--",
    T.reservedOpNames = ["+", "-", "*", "/", "==", "/=", "<", "<=", ">", ">=", "&&", "||", "!", "="],
    T.reservedNames   = ["int", "double", "string", "void", "if", "else", "while", "read", "print", "return"]
  }


lexico = T.makeTokenParser definicao
numero = T.naturalOrFloat lexico
simbolo = T.symbol lexico
parenteses = T.parens lexico
operator = T.reservedOp lexico
literal = T.stringLiteral lexico
identifier = T.identifier lexico
comando = T.comma lexico
reserved = T.reserved lexico
braces = T.braces lexico
pontovirgula = T.semi lexico

prefix name fun = Prefix (do {operator name; return fun})
binario name fun = Infix  (do {operator name; return fun})

tabela =[[prefix "-" Neg],
    [binario "*" (:*:) AssocLeft,
     binario "/" (:/:) AssocLeft],
    [binario "+" (:+:) AssocLeft,
     binario "-" (:-:) AssocLeft]]

tabelaL = [[prefix "!" Not],
    [binario "&&" (:&:) AssocLeft,
     binario "||" (:|:) AssocLeft]]

opr = do {(operator "==" >> return (:==:))
      <|> (operator ">=" >> return (:>=:))
      <|> (operator "<=" >> return (:<=:))
      <|> (operator ">" >> return (:>:))
      <|> (operator "<" >> return (:<:))
      <|> (operator "/=" >> return (:/=:))
      <?> "operador relacional"}

list element = sepBy element comando

fator = do parenteses expr
        <|> constant 
        <|> Lit <$> literal 
        <|> try (do
              i <- identifier;
              e <- parenteses (list expr)
              return (Chamada i e) 
            )
        <|> IdVar <$> identifier 
        <?> "expressao simples"

constant = do {c <- numero; case c of Left  n -> return (Const (CInt n)); Right n -> return (Const (CDouble n))}

expr = buildExpressionParser tabela fator 
       <?> "expressao"

exprR = do {
    e <- expr;
    r <- opr; 
    r e <$> expr;}

exprL = do {parenteses logic 
        <|> Rel 
        <$> exprR}
logic = buildExpressionParser tabelaL exprL 
      <?> "expressao logica"

tipo = do {(reserved "int" >> return TInt) 
          <|> (reserved "double" >> return TDouble) 
          <|> (reserved "string" >> return TString) 
          <?> "type"}

parametro = do
        t <- tipo
        i <- identifier
        return (i :#: t)

parametros = list parametro

declaration = do
        t <- tipo
        i <- list identifier
        pontovirgula
        return (map (:#: t) i)

tipoRetorno = do
    tipo 
    <|> (reserved "void" >> return TVoid) 
    <?> "type"

funcao = do
    tipo <- tipoRetorno
    id <- identifier
    p <- parenteses parametros
    bloco <- braces bloco'
    return (id :->: (p, tipo), (id, concat (fst bloco), snd bloco))

funcoes = do {f <- many funcao;
          return (unzip f)}

blocoPrincipal = do 
                b <- braces bloco'
                return (concat (fst b), snd b)

bloco' = do 
            d <- many declaration
            c <- many comando'
            return (d, c)

bloco = braces (many comando')


-- comandos
comandoif = try (do
            reserved "if"
            l <- parenteses logic
            b <- bloco
            reserved "else"
            If l b <$> bloco
            ) 
            <|> do
                reserved "if"
                l <- parenteses logic
                b <- bloco
                return (If l b [])

comandowhile = do 
                reserved "while"
                l <- parenteses logic
                While l <$> bloco

atribuicao = do 
             i <- identifier
             operator "="
             e <- expr
             pontovirgula
             return (Atrib i e)

comandoread = do
              reserved "read"
              i <- parenteses identifier
              pontovirgula
              return (Leitura i)

comandoprint = do
               reserved "print"
               e <- parenteses expr
               pontovirgula
               return (Print e)

comandoreturn = try (do
                reserved "return"
                e <- expr
                pontovirgula
                return (Ret (Just e))
                ) 
                <|> do 
                    reserved "return"
                    pontovirgula
                    return (Ret Nothing)

comandocall = do
              i <- identifier
              e <- parenteses (list expr)
              pontovirgula
              return (Proc i e)

callatrib = try atribuicao 
            <|> comandocall

comando' = do 
            comandoif 
          <|> comandowhile 
          <|> callatrib 
          <|> comandoread 
          <|> comandoprint 
          <|> comandoreturn 
          <?> "command"

-- programa
program = do 
          f <- funcoes
          m <- blocoPrincipal
          return (Prog (fst f) (snd f) (fst m) (snd m))


-- main
programa = do
      e <- program
      eof
      return e

parser string = case runParser programa [] "Expressions" string of 
    Left error -> print error
    Right x -> print x

main = do
    e <- readFile "teste.diq"
    parser e


module SemanticAnalyzer where

import DataTypes
import GHC.Exts.Heap (GenClosure (fun))
import Parser

data M a where
  MS :: (String, a) -> M a
  deriving (Show)

instance Functor M where
  fmap :: (a -> b) -> M a -> M b
  fmap f (MS (s, a)) = MS (s, f a)

instance Applicative M where
  pure :: a -> M a
  pure a = MS ("", a)
  (<*>) :: M (a -> b) -> M a -> M b
  MS (s, f) <*> MS (t, x) = MS (s ++ t, f x)

instance Monad M where
  (>>=) :: M a -> (a -> M b) -> M b
  MS m >>= f =
    let (s, a) = m
     in let MS (t, b) = f a
         in MS (s ++ t, b)

formatM :: (Show a) => M a -> String
formatM (MS (s, a)) = "Mensagens::\n" ++ s ++ "\nAST: " ++ show a

emitError :: (Show a) => String -> a -> M ()
emitError msg line =
  MS ("ERRO -> " ++ msg ++ " em:\n\t" ++ show line ++ "\n\n", ())

emitWarning :: (Show a) => String -> a -> M ()
emitWarning msg line =
  MS ("ALERTA -> " ++ msg ++ " em:\n\t" ++ show line ++ "\n\n", ())

lookupVar :: Id -> [Var] -> Maybe Tipo
lookupVar id [] = Nothing
lookupVar id ((idd :#: (varType, _)) : xs)
  | id == idd = Just varType
  | otherwise = lookupVar id xs

lookupFunction :: Id -> [Funcao] -> Maybe Funcao
lookupFunction id [] = Nothing
lookupFunction id (fun@(idd :->: (_, _)) : xs)
  | id == idd = Just fun
  | otherwise = lookupFunction id xs

exprTypeCheck :: [Funcao] -> [Var] -> Expr -> M (Tipo, Expr)
exprTypeCheck listaFuncoes _ (Const (CInt n)) = pure (TInt, Const (CInt n))
exprTypeCheck listaFuncoes _ (Const (CDouble n)) = pure (TDouble, Const (CDouble n))
exprTypeCheck listaFuncoes _ (Lit s) = pure (TString, Lit s)
exprTypeCheck listaFuncoes vars (IdVar id) =
  case lookupVar id vars of
    Just varType -> pure (varType, IdVar id)
    Nothing -> do
      emitError ("variavel " ++ show id ++ " não declarada") (IdVar id)
      return (TVoid, IdVar id)
exprTypeCheck listaFuncoes vars (Neg e) = do
  (t, new_e) <- exprTypeCheck listaFuncoes vars e
  if (t == TString)
    then do
      emitError "não é possivel tornar uma string negativa" e
      return (TVoid, e)
    else pure (t, new_e)
exprTypeCheck listaFuncoes vars (IntDouble e) = do
  (t, new_e) <- exprTypeCheck listaFuncoes vars e
  if t == TInt
    then pure (TDouble, IntDouble new_e)
    else do
      emitError "não é possivel converter não-int para double" e
      return (t, new_e)
exprTypeCheck listaFuncoes vars (DoubleInt e) = do
  (t, new_e) <- exprTypeCheck listaFuncoes vars e
  if t == TDouble
    then pure (TInt, DoubleInt new_e)
    else do
      emitError "não é possivel converter não-double para int" e
      return (t, new_e)
exprTypeCheck listaFuncoes vars (Add e1 e2) = checkExpr listaFuncoes vars e1 e2 Add
exprTypeCheck listaFuncoes vars (Sub e1 e2) = checkExpr listaFuncoes vars e1 e2 Sub
exprTypeCheck listaFuncoes vars (Mul e1 e2) = checkExpr listaFuncoes vars e1 e2 Mul
exprTypeCheck listaFuncoes vars (Div e1 e2) = checkExpr listaFuncoes vars e1 e2 Div
exprTypeCheck listaFuncoes vars (Chamada id exprList) =
  case lookupFunction id listaFuncoes of
    Just f -> do
      let (_ :->: (_, returnType)) = f
      new_exprList <- checkFunctionCall listaFuncoes vars f f exprList
      return (returnType, Chamada id new_exprList)
    Nothing -> do
      emitError ("Função" ++ show id ++ " não está declarada") (Chamada id exprList)
      return (TVoid, Chamada id exprList)
exprTypeCheck _ _ expr = do
  emitError "Não implementado" expr
  return (TVoid, expr)

checkExpr :: [Funcao] -> [Var] -> Expr -> Expr -> (Expr -> Expr -> Expr) -> M (Tipo, Expr)
checkExpr listaFuncoes vars e1 e2 operation = do
  (t1, new_e1) <- exprTypeCheck listaFuncoes vars e1
  (t2, new_e2) <- exprTypeCheck listaFuncoes vars e2
  let op = operation e1 e2
  let new_op = operation new_e1 new_e2
  case (t1, t2) of
    (TString, _) -> do
      emitError ("não é possivel operar string com " ++ show t2) op
      return (TString, new_op)
    (_, TString) -> do
      emitError ("não é possível operar " ++ show t1 ++ " com string") op
      return (TString, new_op)
    (TInt, TInt) -> pure (TInt, new_op)
    (TDouble, TDouble) -> pure (TDouble, new_op)
    (TInt, TDouble) -> do
      emitWarning "coerção int para double" op
      return (TDouble, operation (IntDouble new_e1) new_e2)
    (TDouble, TInt) -> do
      emitWarning "coerção double para int" op
      return (TDouble, operation new_e1 (IntDouble new_e2))
    _ -> do
      emitError "tipos não suportados" op
      return (TVoid, new_op)

exprRTypeCheck :: [Funcao] -> [Var] -> ExprR -> M ExprR
exprRTypeCheck listaFuncoes vars (Req e1 e2) = checkExprR listaFuncoes vars e1 e2 Req
exprRTypeCheck listaFuncoes vars (Rdif e1 e2) = checkExprR listaFuncoes vars e1 e2 Rdif
exprRTypeCheck listaFuncoes vars (Rlt e1 e2) = checkExprR listaFuncoes vars e1 e2 Rlt
exprRTypeCheck listaFuncoes vars (Rgt e1 e2) = checkExprR listaFuncoes vars e1 e2 Rgt
exprRTypeCheck listaFuncoes vars (Rle e1 e2) = checkExprR listaFuncoes vars e1 e2 Rle
exprRTypeCheck listaFuncoes vars (Rge e1 e2) = checkExprR listaFuncoes vars e1 e2 Rge

checkExprR :: [Funcao] -> [Var] -> Expr -> Expr -> (Expr -> Expr -> ExprR) -> M ExprR
checkExprR listaFuncoes vars e1 e2 operation = do
  (t1, new_e1) <- exprTypeCheck listaFuncoes vars e1
  (t2, new_e2) <- exprTypeCheck listaFuncoes vars e2
  let op = operation e1 e2
  let new_op = operation new_e1 new_e2
  case (t1, t2) of
    (TString, TString) -> pure new_op
    (TString, _) -> do
      emitError ("não é possível comparar string com " ++ show t2) op
      return new_op
    (_, TString) -> do
      emitError ("não é possível comparar " ++ show t1 ++ " com string") op
      return new_op
    (TInt, TInt) -> pure new_op
    (TDouble, TDouble) -> pure new_op
    (TInt, TDouble) -> do
      emitWarning "coerção int para double" op
      return (operation (IntDouble new_e1) new_e2)
    (TDouble, TInt) -> do
      emitWarning "coerção int para double" op
      return (operation new_e1 (IntDouble new_e2))
    _ -> do
      emitError "tipos não suportados" op
      return new_op

exprLTypeCheck :: [Funcao] -> [Var] -> ExprL -> M ExprL
exprLTypeCheck listaFuncoes vars (And e1 e2) = checkExprL listaFuncoes vars e1 e2 And
exprLTypeCheck listaFuncoes vars (Or e1 e2) = checkExprL listaFuncoes vars e1 e2 Or
exprLTypeCheck listaFuncoes vars (Not e1) = exprLTypeCheck listaFuncoes vars e1
exprLTypeCheck listaFuncoes vars (Rel e1) = pure Rel <*> exprRTypeCheck listaFuncoes vars e1

checkExprL :: [Funcao] -> [Var] -> ExprL -> ExprL -> (ExprL -> ExprL -> b) -> M b
checkExprL listaFuncoes vars e1 e2 operation = do
  new_e1 <- exprLTypeCheck listaFuncoes vars e1
  new_e2 <- exprLTypeCheck listaFuncoes vars e2
  let new_op = operation new_e1 new_e2
  pure new_op

checarVariaveisBloco :: [Var] -> [Var] -> M [Var]
checarVariaveisBloco [] _ = pure []
checarVariaveisBloco (var : xs) varList = do
  tail <- checarVariaveisBloco xs varList
  if varFreq var varList > 1
    then do
      emitError ("Variável " ++ show var ++ " declarada multiplas vezes") varList
      pure (var : tail)
    else pure (var : tail)

checarBloco :: [Funcao] -> Maybe Funcao -> [Var] -> Bloco -> M Bloco
checarBloco listaFuncoes f vars (cmd : bloco) = do
  new_cmd <- checkCommand listaFuncoes f vars cmd
  new_block <- checarBloco listaFuncoes f vars bloco
  pure (new_cmd : new_block)
checarBloco _ f vars [] = pure []

checkCommand :: [Funcao] -> Maybe Funcao -> [Var] -> Comando -> M Comando
checkCommand listaFuncoes f vars (If exprL b1 b2) = do
  new_e <- exprLTypeCheck listaFuncoes vars exprL
  new_b1 <- checarBloco listaFuncoes f vars b1
  new_b2 <- checarBloco listaFuncoes f vars b2
  pure (If new_e new_b1 new_b2)
checkCommand listaFuncoes f vars (While exprL b) = do
  new_e <- exprLTypeCheck listaFuncoes vars exprL
  new_b <- checarBloco listaFuncoes f vars b
  pure (While new_e new_b)
checkCommand listaFuncoes _ vars (Imp expr) = do
  (_, new_e) <- exprTypeCheck listaFuncoes vars expr
  pure (Imp new_e)
checkCommand listaFuncoes _ vars (Atrib var expr) = do
  (eType, new_expr) <- exprTypeCheck listaFuncoes vars expr
  let cmd = Atrib var expr
  let new_cmd = Atrib var new_expr
  case lookupVar var vars of
    Just varType -> do
      case (varType, eType) of
        (TString, TString) -> pure new_cmd
        (TString, _) -> do
          emitError ("não é possível atribuir " ++ show eType ++ " para string") cmd
          return new_cmd
        (_, TString) -> do
          emitError ("não é possível atribuir string para " ++ show eType) cmd
          return new_cmd
        (TInt, TInt) -> pure new_cmd
        (TDouble, TDouble) -> pure new_cmd
        (TInt, TDouble) -> do
          emitWarning "coerção double para int" cmd
          return (Atrib var (DoubleInt expr))
        (TDouble, TInt) -> do
          emitWarning "coerção int para double" cmd
          return (Atrib var (IntDouble expr))
        _ -> do
          emitError "tipos não suportados" cmd
          return new_cmd
    Nothing -> do
      emitError ("variavel " ++ show var ++ " nao declarada") new_cmd
      return cmd
checkCommand _ _ vars (Leitura var) = do
  let cmd = Leitura var
  case lookupVar var vars of
    Just varType -> pure cmd
    Nothing -> do
      emitError ("variavel " ++ show var ++ " nao declarada") cmd
      return cmd
checkCommand listaFuncoes f vars (Ret maybeExpr) = do
  let returnType = case f of
        Just (id :->: (_, retType)) -> retType
        Nothing -> TVoid
  let cmd = Ret maybeExpr
  case maybeExpr of
    Just e -> do
      (eType, new_e) <- exprTypeCheck listaFuncoes vars e
      case (eType, returnType) of
        (TString, TString) -> pure (Ret (Just new_e))
        (TString, _) -> do
          emitError (show eType ++ " não bate com o tipo esperado " ++ show returnType) cmd
          return (Ret (Just new_e))
        (_, TString) -> do
          emitError (show eType ++ " não bate com o tipo esperado " ++ show returnType) cmd
          return (Ret (Just new_e))
        (TInt, TInt) -> pure (Ret (Just new_e))
        (TDouble, TDouble) -> pure (Ret (Just new_e))
        (TInt, TDouble) -> do
          emitWarning "coerção double para int" cmd
          return (Ret (Just (DoubleInt new_e)))
        (TDouble, TInt) -> do
          emitWarning "coerção int para double" cmd
          return (Ret (Just (IntDouble new_e)))
        _ -> do
          emitError "tipos não suportados" cmd
          return (Ret (Just new_e))
    Nothing ->
      case returnType of
        TVoid -> pure cmd
        _ -> do
          emitError "função não nula espera retorno" cmd
          pure cmd
checkCommand listaFuncoes f vars (Proc id exprList) =
  case lookupFunction id listaFuncoes of
    Just fun -> do
      new_exprList <- checkFunctionCall listaFuncoes vars fun fun exprList
      return (Proc id new_exprList)
    Nothing -> do
      emitError ("função " ++ show id ++ " não declarada") (Proc id exprList)
      return (Proc id exprList)

checkFunctionCall :: [Funcao] -> [Var] -> Funcao -> Funcao -> [Expr] -> M [Expr]
checkFunctionCall listaFuncoes vars originalFunction (_ :->: ([], _)) [] = pure []
checkFunctionCall listaFuncoes vars originalFunction f exprList = do
  let (funId :->: (functionVarList, returnType)) = f
  if length functionVarList < length exprList
    then do
      emitError ("argumentos demais (" ++ show exprList ++ ") na chamada da função") originalFunction
      return exprList
    else
      if length functionVarList > length exprList
        then do
          emitError ("argumentos de menos (" ++ show exprList ++ ") na chamada da função") originalFunction
          return exprList
        else do
          let (functionVar : rest) = functionVarList
          let (id :#: (varType, _mem)) = functionVar
          let (expr : exprTail) = exprList
          let nxtFunction = funId :->: (rest, returnType)
          (eType, new_expr) <- exprTypeCheck listaFuncoes vars expr
          newTail <- checkFunctionCall listaFuncoes vars originalFunction nxtFunction exprTail
          case (varType, eType) of
            (TString, TString) -> pure (new_expr : newTail)
            (TString, _) -> do
              emitError ("não é possivel coagir" ++ show eType ++ " para  string") originalFunction
              return (new_expr : newTail)
            (_, TString) -> do
              emitError ("não é possivel coagir string para " ++ show eType) originalFunction
              return (new_expr : newTail)
            (TInt, TInt) -> pure (new_expr : newTail)
            (TDouble, TDouble) -> pure (new_expr : newTail)
            (TInt, TDouble) -> do
              emitWarning "coerção double para int" originalFunction
              return (DoubleInt new_expr : newTail)
            (TDouble, TInt) -> do
              emitWarning "coerção int para double" originalFunction
              return (IntDouble new_expr : newTail)
            _ -> do
              emitError "tipos não suportados" originalFunction
              return (new_expr : newTail)

functionFreq :: Funcao -> [Funcao] -> Int
functionFreq f [] = 0
functionFreq f@(id :->: (_, _)) ((id2 :->: (_, _)) : xs)
  | id == id2 = 1 + functionFreq f xs
  | otherwise = functionFreq f xs

varFreq :: Var -> [Var] -> Int
varFreq var [] = 0
varFreq var@(id :#: (_, _)) ((id2 :#: (_, _)) : xs)
  | id == id2 = 1 + varFreq var xs
  | otherwise = varFreq var xs

checaFuncao :: [Funcao] -> Funcao -> (Id, [Var], Bloco) -> M (Funcao, (Id, [Var], Bloco))
checaFuncao listaFuncoes f (id, vars, bloco) = do
  new_block <- checarBloco listaFuncoes (Just f) vars bloco
  if functionFreq f listaFuncoes > 1
    then do
      emitError ("função " ++ show f ++ " declarada  multiplas vezes") listaFuncoes
      pure (f, (id, vars, new_block))
    else pure (f, (id, vars, new_block))

checarListaFuncoes :: [Funcao] -> [Funcao] -> [(Id, [Var], Bloco)] -> M ([Funcao], [(Id, [Var], Bloco)])
checarListaFuncoes _ [] [] = pure ([], [])
checarListaFuncoes listaFuncoes (f : fTail) ((id, vars, bloco) : rest) = do
  novasVariaveis <- checarVariaveisBloco vars vars
  (new_function, new_functionBody) <- checaFuncao listaFuncoes f (id, novasVariaveis, bloco)
  (tail_functionList, tail_functionBodyList) <- checarListaFuncoes listaFuncoes fTail rest
  pure (new_function : tail_functionList, new_functionBody : tail_functionBodyList)

checaPrograma :: Programa -> M Programa
checaPrograma (Prog listaFuncoes listaBlocos variaveisBlocoPrincipal blocoPrincipal) = do
  novoVariaveisBlocoPrincipal <- checarVariaveisBloco variaveisBlocoPrincipal variaveisBlocoPrincipal
  (nova_listaFuncoes, nova_listaBlocoFuncoes) <- checarListaFuncoes listaFuncoes listaFuncoes listaBlocos
  novo_blocoPrincipal <- checarBloco listaFuncoes Nothing variaveisBlocoPrincipal blocoPrincipal
  pure (Prog nova_listaFuncoes nova_listaBlocoFuncoes variaveisBlocoPrincipal novo_blocoPrincipal)

analiseSemantica :: IO ()
analiseSemantica = do
  putStrLn "entre com o caminho do arquivo:"
  file <- getLine
  code <- readFile file
  writeFile "resultadoAnaliseSemantica" (formatM $ checaPrograma $ buildAST code)
  putStrLn "feito! resultado em 'resultadoAnaliseSemantica'"
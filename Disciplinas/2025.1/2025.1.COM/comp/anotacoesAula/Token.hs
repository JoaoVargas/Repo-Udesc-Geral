module Token where

data Token
  = NUM Double
  | BOOLEAN Bool
  | ADD
  | SUB
  | MUL
  | DIV
  | LPAR
  | RPAR
  | AND
  | OR
  | NOT
  | EQUAL
  | NOT_EQAL
  | GREATER_THAN_EQUAL
  | LESS_THAN_EQUAL
  | GREATER_THAN
  | LESS_THAN
  deriving (Eq, Show)

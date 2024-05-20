class Filme:
    
  def __init__(self, titulo:str = None, ano:int = None, classificacao:str = None, nota:float = None) -> None:
    self.__titulo = titulo
    self.__ano = ano
    self.__classificacao = classificacao
    self.__nota = nota
    
  
    
  def getTitulo(self) -> str:
    return self.__titulo
    
  def __str__(self) -> str:
    return self.nome
class Pessoa:
  def __init__(self, nome: str):
    self.nome = nome
    
    
class Aluno(Pessoa):
  def __init__(self, nome: str, n1: float, n2: float, n3: float, n4: float, n5: float):
    super().__init__(nome)
    self.__notas = [n1, n2, n3, n4, n5] 
    
  def calcularMedia(self) -> float:
    aux = 0
    
    for i in self.__notas:
      aux += i
    
    return aux/5
  
  def __repr__(self) -> str:
    return self.nome + " - Media: " + str(self.calcularMedia())
  
  
class Professor(Pessoa):
  def __init__(self, nome: str, salario: float):
    super().__init__(nome)
    self.salario = salario
    
  def __repr__(self) -> str:
    return self.nome + " - Salario: " + str(self.salario)
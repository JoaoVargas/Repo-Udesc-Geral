class Bicicleta:
  def __init__(self, marca: str) -> None:
    self.__marca = marca
  
  def __str__(self) -> str:
    return 'A bicicleta: {}'.format(self.__marca)
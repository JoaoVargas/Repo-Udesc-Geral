class Carro:
  def __init__(self, marca: str):
    self.marca = marca

  def __str__(self) -> str:
    return 'O carro: {}'.format(self.marca)
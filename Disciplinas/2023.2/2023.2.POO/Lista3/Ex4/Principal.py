import Pessoa

aluno1 = Pessoa.Aluno("Aluno1", 1, 0, 0, 0, 0)
aluno2 = Pessoa.Aluno("Aluno2", 5, 5, 5, 5, 5)
aluno3 = Pessoa.Aluno("Aluno3", 1, 2, 3, 4, 5)
aluno4 = Pessoa.Aluno("Aluno4", 10, 9, 8, 7, 6)
aluno5 = Pessoa.Aluno("Aluno5", 10, 10, 10, 10, 10)

professor1 = Pessoa.Professor("Professor1", 999)
professor2 = Pessoa.Professor("Professor2", 0)

print(aluno1.__repr__())
print(aluno2.__repr__())
print(aluno3.__repr__())
print(aluno4.__repr__())
print(aluno5.__repr__())
print(professor1.__repr__())
print(professor2.__repr__())
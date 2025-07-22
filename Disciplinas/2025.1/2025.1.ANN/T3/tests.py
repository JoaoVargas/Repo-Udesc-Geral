import numpy as np
import sympy as sp
from typing import Tuple
import matplotlib.pyplot as plt



def tabela_diferencas_divididas(
    tabela_entrada: np.ndarray
) -> np.ndarray:
    """
    Calcula a tabela de diferenças divididas de Newton.
    Args:
        tabela_entrada: matriz Nx2, onde cada linha é [x, y]
    Retorno:
        Tabela de diferenças divididas (numpy array)
    """
    # Extrai os valores de x e f(x) da tabela de entrada
    x_i: np.ndarray = tabela_entrada[:, 0]
    fx_i: np.ndarray = tabela_entrada[:, 1]

    # Número de pontos na tabela
    numero_pontos: int = len(x_i)

    # Cria matriz para armazenar as diferenças divididas
    tabela_dd: np.ndarray = np.zeros((numero_pontos, numero_pontos + 1))
    tabela_dd[:, 0] = x_i
    tabela_dd[:, 1] = fx_i

    # Calcula as diferenças divididas para cada ordem
    for n in range(2, numero_pontos + 1):
        for l in range(numero_pontos - (n - 1)):
            numerador: float = tabela_dd[l + 1][n - 1] - tabela_dd[l][n - 1]
            denominador: float = tabela_dd[l + n - 1][0] - tabela_dd[l][0]
            tabela_dd[l][n] = numerador / denominador

    return tabela_dd
  
  
  
def polinomio_newton(
    tabela_dd: np.ndarray
) -> sp.Expr:
    """
    Monta o polinômio interpolador de Newton a partir da tabela de diferenças divididas.
    Args:
        tabela_dd: tabela de diferenças divididas
    Retorno:
        Expressão simbólica do polinômio (SymPy)
    """
    x: sp.Symbol = sp.symbols('x')  # Variável simbólica
    x_i: np.ndarray = tabela_dd[:, 0]
    numero_pontos: int = len(x_i)

    polinomio: sp.Expr = 0  # Inicializa o polinômio

    # Monta o polinômio de Newton termo a termo
    for n in range(numero_pontos):
        termo: sp.Expr = 1
        # Produto dos termos (x - x_i) até o termo n-1
        for t in range(n):
            termo *= (x - tabela_dd[t][0])
        # Soma o termo multiplicado pelo coeficiente correspondente
        polinomio += termo * tabela_dd[0][n + 1]

    return polinomio
  
  
  
def newton_interpolation(
    tabela_entrada: np.ndarray,
    x_interpolado: float
) -> Tuple[float, np.ndarray]:
    """
    Interpolação polinomial pelo método de Newton (diferenças divididas).
    Args:
        tabela_entrada: matriz Nx2, onde cada linha é [x, y]
        x_interpolado: valor de x para interpolar
    Retorno:
        Valor interpolado (float)
        Tabela de diferenças divididas (numpy array)
    """
    # Calcula a tabela de diferenças divididas
    tabela_dd: np.ndarray = tabela_diferencas_divididas(tabela_entrada)
    print('Tabela de Diferenças Divididas gerada:')
    print(tabela_dd)
    print()

    # Monta o polinômio de Newton
    polinomio: sp.Expr = polinomio_newton(tabela_dd)
    print('Polinômio expandido:')
    print(polinomio)
    print()

    print('Polinômio simplificado:')
    print(sp.expand(polinomio))
    print()

    # Converte o polinômio simbólico para função numérica
    polinomio_lambidificado = sp.lambdify(sp.symbols('x'), polinomio, "numpy")

    # Avalia o polinômio no ponto desejado
    polinomio_resolvido: float = float(polinomio_lambidificado(x_interpolado))

    return polinomio_resolvido, tabela_dd



# Tabela de entrada: cada linha é [x, f(x)]
tabela_entrada: np.ndarray = np.array([
    [0.34, 0.22],
    [0.4, 0.27],
    [0.52, 0.31],
    [0.6, 0.34]
])

# Valor de x para interpolar
x_interpolado: float = 0.47

# Plotar o grafico de dispersão
eixo_x = tabela_entrada[:, 0]
eixo_y = tabela_entrada[:, 1]
plt.scatter(eixo_x, eixo_y, color='blue', label='Dados')
plt.xlabel('x')
plt.ylabel('f(x)')
plt.title('Gráfico de dispersão dos dados')
plt.grid(True)
plt.legend()
plt.show()
print()

# Realiza a interpolação de Newton
resultado: float
divided_diff: np.ndarray
resultado, divided_diff = newton_interpolation(tabela_entrada, x_interpolado)

# Exibe o valor interpolado
print(f"Valor interpolado em {x_interpolado} -> {resultado}")
print()


# Plotar o grafico de dispersão com interpolado
eixo_x = tabela_entrada[:, 0]
eixo_y = tabela_entrada[:, 1]
plt.scatter(eixo_x, eixo_y, color='blue', label='Dados')
plt.scatter([x_interpolado], [resultado], color='red', label=f'Interpolado ({x_interpolado:}, {resultado})')
plt.xlabel('x')
plt.ylabel('f(x)')
plt.title('Gráfico de dispersão dos dados')
plt.grid(True)
plt.legend()
plt.show()
print()

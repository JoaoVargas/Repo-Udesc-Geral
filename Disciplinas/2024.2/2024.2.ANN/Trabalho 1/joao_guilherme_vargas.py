def zero_metodo_bissecao(f, a, b, tol=1e-5, max_iteracao=100, espec_iteracao=None):
    """
    Parameters:
    f : function
        Função contínua.
    a : float
        Intervalo inferior.
    b : float
        Intervalo superior.
    tol : float, opcional
        Tolerância, por padrão 1e-5.
    max_iteracao : int, opcional
        Numero maximo de iterações.
    espec_iteracao : int, opcional
        Iteração específica para devolver o valor de xk e erro absoluto maximo.
    
    Returns:
    tuple(float, float)
        Valor de xk e erro absoluto maximo.
    """
    
    if f(a) * f(b) > 0:
        raise ValueError("O intervalo não segue o teorema de Bolzano")

    iteracao = 0
    xk = (a + b) / 2.0

    while (b - a) / 2 > tol and iteracao < max_iteracao:
        xk = (a + b) / 2.0
        
        # Erro absoluto maximo
        max_error = (b - a) / 2.0
        
        # Check se iteração especifica
        if iteracao == espec_iteracao:
            return xk, max_error, f(xk)

        # Checagem
        if f(xk) == 0:
            return xk, max_error, f(xk) # Achou o zero
        elif f(a) * f(xk) < 0:
            b = xk  # Zero em [a, xk]
        else:
            a = xk  # Zero em [xk, b]
        
        iteracao += 1
        
    return xk, (b - a) / 2.0, f(xk)

if __name__ == "__main__":
    def f(x):
        return x**5 - 3072 * x + 2504
    interval = [0,1]
    
    """
    Testes
    for i in range(9):
      xi, max_error_xi, fxi = zero_metodo_bissecao(f, interval[0], interval[1], espec_iteracao=i)
      
      print(f"O valor na iteração x{i} é: \n{xi}")
      print(f"Maximo erro absoluto da aproximação x{i} é: \n{max_error_xi}")
      print(f"f(x{i}) é: \n{fxi}\n")
      
    xk, max_error_xk, fxk = zero_metodo_bissecao(f, interval[0], interval[1], tol=1e-6)
    print(f"Com tolerância de 1e-6")
    print(f"O valor na iteração xk é: \n{xk}")
    print(f"Maximo erro absoluto da aproximação xk é: \n{max_error_xk}")
    print(f"f({xk}) é: \n{fxk}\n")
    """
    
    xk, max_error_xk, fxk = zero_metodo_bissecao(f, interval[0], interval[1], espec_iteracao=8)
    print(xk)
    print(max_error_xk)
    print(fxk)
      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

import os
os.system('cls' if os.name == 'nt' else 'clear')

def verifica_AFD(delta, sigma, Q):
    nlinhas = len(delta)
    #ncolunas = len(delta[0]) # 3
    print("Total de transições: ", nlinhas)
    for elemento in sigma:
        for estado in Q:
            contagem = 0
            for i in range(0, nlinhas):
                transicao = delta[i]
                if((transicao[0] == estado) and (transicao[1] == elemento)):
                    contagem = contagem + 1
                    #print("=>>", transicao, contagem, elemento, estado, nlinhas)
            if(contagem > 1):
                return 0
    return 1

# Quíntupla
sigma = ["a", "b"]
Q = [1, 2]
q0 = 1
F = [1,2]
delta = [[1, "a", 2], [2, "a", 2], [2, "b", 2]]

# Definição estática da fita
fita = "ba"

# Verificação - AFD
if(verifica_AFD(delta, sigma, Q)):
    print(">> Trata-se de um AFD!")
    
    # Inicialização - simulação de um AFD (sem validações)
    q_atual = q0
    tamanho = len(fita)
    tamanho_delta = len(delta)
    tamanho_F = len(F)
    tamanho_Q = len(Q)
    tamanho_sigma= len(sigma)
    pos_fita = 0
    indef = 0
    k = 0
    subpalavra = ""

    # Fita
    print("\n===========\nFita:", fita, end='')
    print("\n===========")
    # Alfabeto
    print("Alfabeto: { ", end='')
    for i in range(0, tamanho_Q):
        print(f"{sigma[i]}", end='')
        if(i < tamanho_Q - 1):
            print(", ", end='')
        else:
            print(" }", end='')
    # Q
    print("\nEstados: { ", end='')
    for i in range(0, tamanho_Q):
        print(f"{Q[i]}", end='')
        if(i < tamanho_Q - 1):
            print(", ", end='')
        else:
            print(" }", end='')
    # q0
    print("\nEstado inicial:", q0)
    # Identificação das transições
    print("Transições:", end='')
    for i in range(0, tamanho_delta):
        print(f"\n{i+1}: {delta[i]}", end='')
    # Estados finais
    print("\nEstados finais: { ", end='')
    for i in range(0, tamanho_F):
        print(f"{F[i]}", end='')
        if(i < tamanho_Q - 1):
            print(", ", end='')
        else:
            print(" }", end='')
    print("\n===========")
    print("\nEvolução (estados): ", end='')
    print(f"|{q_atual}>", end='')

    # Condição básica - simulação de um AFD
    while((pos_fita < tamanho) and (indef != 1)): # Veja o slide 20 / aula03
        # i representa a posição em delta (seleção de uma transição)
        i = 0
        encontrado = 0
        while((i < len(delta)) and (encontrado == 0) and (k < tamanho)):
            t = delta[i]
            if((fita[k] == t[1]) and (q_atual == t[0])):
                encontrado = 1
                print(f" {fita[k]} ", end='')
                k = k + 1
                q_atual = t[2]
                print(f"<{q_atual}>", end='')
                pos_fita = pos_fita + 1
                if(len(subpalavra) != (len(fita)-1)):
                    subpalavra = fita[k] + subpalavra
            else:
                i = i + 1
        if(encontrado == 0):
            indef = 1

    if ((indef == 1) or (q_atual not in F)):
        print(f"\nA palavra {fita} não foi reconhecida pelo AFD!")
        print("\nSubpalvra processada: ", end='')
        if(subpalavra != ""):
            print(subpalavra)
        else:
            print("palavra vazia.")
        if(q_atual in F):
            print("A subpalavra faz parte da linguagem!")
        else:
            print("A subpalavra não faz parte da linguagem!")

    else:
        if(len(fita) == 0):
            print(f"\nA palavra vazia foi reconhecida pelo AFD!\n")
        else:
            print(f"\nA palavra \"{fita}\" foi reconhecida pelo AFD!\n")
else:
    print(">> Trata-se de um AFN!")
    tamanho_delta = len(delta)
    tamanho_F = len(F)
    tamanho_Q = len(Q)
    
    # Fita
    print("\n===========\nFita:", fita, end='')
    print("\n===========")
    # Alfabeto
    print("Alfabeto: ", end='')
    for i in range(0, tamanho_Q):
        print(f"{sigma[i]} ", end='')
    # Q
    print("\nEstados: [", end='')
    for i in range(0, tamanho_Q):
        print(f"{Q[i]} ", end='')
    print(" ]", end='')
    # q0
    print("\nEstado inicial:", q0)
    # Identificação das transições
    print("Transições:", end='')
    for i in range(0, tamanho_delta):
        print(f"\n{i+1}: {delta[i]}", end='')
    # Estados finais
    print("\nEstados finais: ", end='')
    for i in range(0, tamanho_F):
        print(f"{F[i]} ", end='')
    print("\n===========")
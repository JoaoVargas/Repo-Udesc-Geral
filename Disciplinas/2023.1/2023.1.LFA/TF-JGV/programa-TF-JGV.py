import importlib  
aux = importlib.import_module("aux-TF-JGV")



#gera uma lista de producoes onde o estado dado aparece
def gerar_producoes_possiveis(estado_atual, producoes): 
    lista_gerada = []
    for prod in producoes:
        if estado_atual == prod[0]:
            lista_gerada.append(prod)
    return lista_gerada

#printa na tela uma lista enumerada de producoes
def printar_producoes_possiveis(lista_producoes):
    i = 0
    for possiveis in lista_producoes:
        print(i, ". ", possiveis[1])
        i += 1
    i = 0
    print("")

#troca o estado atual pelo estado escolhido numa lista de producoes
def troca_estado(escolha: int, producoes_possiveis: list):
    if escolha >= 0 and escolha < len(producoes_possiveis):
        print("Saída do sistema:", 
              (producoes_possiveis[escolha][3] if not (producoes_possiveis[escolha][3] ==  "ε") else ""), 
              (producoes_possiveis[escolha][4] if not (producoes_possiveis[escolha][4] ==  "ε") else ""),
              "\n\n")
        return producoes_possiveis[escolha][2]
    else:
        print("ERRO: Escolha uma das opções possíveis\n\n")

#check se é um numero
def verificar_numero():
    while True:
        try:
            escolha = int(input())
            if isinstance(escolha, int):
                break
            else:
                raise ValueError
        except ValueError:
            print("ERRO: Use numeros.")
    return escolha

#verifica se um estado esta em um dado dicionario de automato
def verificar_estado(estado, dicionario):
    for i in dicionario["estado"]:
        if i == estado:
            return True
        else:
            return False

#devolve uma lista de producoes possiveis dado um estado
def achar_producoes(estado):
    if verificar_estado(estado, aux.BI1["estado"]):
        return gerar_producoes_possiveis()

def lista_producoes_buffers(entrada):
    lista_saida = []
    for i in aux.lista_buffers:
        for j in i["prod"]:
            if j[1] == entrada:
                lista_saida.append(j)
    return lista_saida

                 

print("Tipos de entrada e saída:\n",
    "<Entrada de usuário>;\n",
    "(Entrada do sistema);\n",
    "Saída para usuário;\n",
    "[Saída para o sistema];\n\n",)

sair = 0
j = 0
while sair == 0:
    ACG_producoes_possiveis = gerar_producoes_possiveis(aux.ACG_atual, aux.ACG_producao)
    buffer_producoes_possiveis = []
    
    for i in ACG_producoes_possiveis:
        buffer_producoes_possiveis.extend(lista_producoes_buffers(i[1]))
    
        
            
    print("Escolha uma das seguintes entradas possiveis:")
    printar_producoes_possiveis(ACG_producoes_possiveis)
    escolha = verificar_numero()
    
    
    aux.ACG_atual = troca_estado(escolha, ACG_producoes_possiveis)
    
    

    

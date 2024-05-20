#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


typedef struct{
    char simbolo;
    bool inicial;
    bool final;
}estado;
typedef struct{
    char simbolo;
    struct estado* estado_ant;
    struct estado* estado_pos;
}acao;


int getTamAlfabeto();
void getAlfabeto(int, char*);
int getTamEstados();
void getEstados(int, char*);
int setEstados(int, char*, estado*);
void setInicial(int, estado*);
void setFinal(int, estado*);


int main(int argc, char const *argv[])
{
    int tamAlfabeto;
    char *alfabeto;
    int tamEstados;
    char *estados;
 //   struct estado *estadosArray;
    acao *acoes;


    printf("--Automato Finito Deterministico--\n");
    printf("Para começar digite os dados da 5-upla\n");

    tamAlfabeto = getTamAlfabeto();
    alfabeto = malloc(tamAlfabeto * sizeof(char));
    getAlfabeto(tamAlfabeto, alfabeto);
    
    tamEstados = getTamEstados();
    estados = malloc(tamEstados * sizeof(char));
    getEstados(tamEstados, estados);

    estado *estadosArray = malloc(tamEstados * sizeof(estado));
    setEstados(tamEstados, estados, estadosArray);

    while (getchar() != '\n');
    setInicial(tamEstados, estadosArray);
    while (getchar() != '\n');
    setFinal(tamEstados, estadosArray);

    for (int i = 0; i < tamEstados; i++)
    {
        printf("%c %d %d\n", estadosArray[i].simbolo, estadosArray[i].inicial, estadosArray[i].final);
    }
    
    

    return 0;
}


int getTamAlfabeto(){
    int tamAlfabeto = 0;

    while (tamAlfabeto < 1){
        printf("Digite o numero de itens do alfabeto: \n");
        printf("Exemplo: 5\n");
        scanf("%d", &tamAlfabeto);

        if (tamAlfabeto < 1){
            printf("Valor invalido, digite um inteiro superior a 0\n");
        }
    }

    return tamAlfabeto;
}

void getAlfabeto(int tamAlfabeto, char* alfabeto){
    while (sizeof(char) * tamAlfabeto != strlen(alfabeto)){
        printf("Digite uma lista de itens do Alfabeto, cada item deve ter 1 caracter, eles NAO devem ser separados e NAO devem se repetir:\n");
        printf("Exemplo: abcde\n");
        scanf("%s", alfabeto);

        if (sizeof(char) * tamAlfabeto != strlen(alfabeto)){
            printf("Lista invalida, o numero de itens da lista nao bate com o descrito\n");
        }
    }
}

int getTamEstados(){
    int tamEstados = 0;

    while (tamEstados < 1){
        printf("Digite o numero de estados\n");
        printf("Exemplo: 3\n");
        scanf("%d", &tamEstados);

        if (tamEstados < 1){
            printf("Valor invalido, digite um inteiro superior a 0\n");
        }
    }

    return tamEstados;
}

void getEstados(int tamEstados, char* estados){
    while (sizeof(char) * tamEstados != strlen(estados)){
        printf("Digite uma lista de estados, cada item deve ter 1 caracter, eles NAO devem ser separados e NAO devem se repetir:\n");
        printf("Exemplo: 012\n");
        scanf("%s", estados);

        if (sizeof(char) * tamEstados != strlen(estados)){
            printf("Lista invalida, o numero de itens da lista nao bate com o descrito\n");
        }
    }
}

int setEstados(int tamEstados, char* estadosLista, estado* estadosArray){
    int i;

    for (i = 0; i < tamEstados; i++){
        estadosArray[i].simbolo = estadosLista[i];
    }
    
}

void setInicial(int tamEstados, estado* estadosArray){
    int i;
    char simbolo;

    printf("Esses sao os estados dados pelo usuario:\n");
    for(i = 0; i < tamEstados; i++){
        printf("%c ", estadosArray[i].simbolo);
    }
    printf("\n");

    printf("Digite qual estado e o inicial, use o simbolo dado anteriormente: \n");
    printf("Exemplo: 0\n");
    scanf("%c", &simbolo);

    for (i = 0; i < tamEstados; i++){
        if (estadosArray[i].simbolo == simbolo){
            estadosArray[i].inicial = true;
        } else {
            estadosArray[i].inicial = false;
        }
    }
}

void setFinal(int tamEstados, estado* estadosArray){
    int i, j, tamSimbolos = 0;
    char* simbolos;

    printf("Esses sao os estados dados pelo usuario:\n");
    for(i = 0; i < tamEstados; i++){
        printf("%c ", estadosArray[i].simbolo);
    }
    printf("\n");

    while (tamSimbolos < 1){
        printf("Digite o numero de estados finais\n");
        printf("Exemplo: 3\n");
        scanf("%d", &tamSimbolos);

        if (tamEstados < 1){
            printf("Valor invalido, digite um inteiro superior a 0\n");
        }
    }
    simbolos = malloc(tamSimbolos * sizeof(char));
    
    while (sizeof(char) * tamSimbolos != strlen(simbolos)){
        printf("Digite uma lista de estados finais, cada item deve ter 1 caracter, eles NAO devem ser separados e NAO devem se repetir:\n");
        printf("Exemplo: 012\n");
        scanf("%s", simbolos);

        if (sizeof(char) * tamSimbolos != strlen(simbolos)){
            printf("Lista invalida, o numero de itens da lista nao bate com o descrito\n");
        }
    }

    for(j = 0; j < tamSimbolos; j++){
        for (i = 0; i < tamEstados; i++){
            printf("%c %c\n", estadosArray[i].simbolo, simbolos[j]);
            if (estadosArray[i].simbolo == simbolos[j]){
                estadosArray[i].final = true;
            } 
        }
    }

    for (i = 0; i < tamEstados; i++){
        printf("%d\n", estadosArray[i].final);
    }
}







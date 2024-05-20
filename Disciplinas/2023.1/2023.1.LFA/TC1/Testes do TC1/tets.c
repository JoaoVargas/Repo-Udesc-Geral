#include <stdio.h>
#include <stdbool.h>

struct estado {
    char simbolo;
    bool inicial;
    bool final;
};

struct acao{
    char simbolo;
    struct estado* estado_ant;
    struct estado* estado_pos;
};

int main() {
    struct estado estados[5];

    estados[0].final = false;
    estados[0].inicial = true;
    estados[0].simbolo = 'A';
    
    estados[1].final = true;
    estados[1].inicial = false;
    estados[1].simbolo = 'B';


    struct acao teste;

    teste.simbolo = 'a';
    teste.estado_ant = &estados[0];
    teste.estado_pos = &estados[1];

    printf("%c --%c--> %c \n\n", 
            teste.estado_ant->simbolo, teste.simbolo, teste.estado_pos->simbolo);
    printf("%c:\nInicial: %d\nFinal: %d\n", 
            teste.estado_ant->simbolo,teste.estado_ant->inicial,teste.estado_ant->final);
    printf("%c:\nInicial: %d\nFinal: %d\n", 
            teste.estado_pos->simbolo,teste.estado_pos->inicial,teste.estado_pos->final);


    return 0;
}
#include <stdio.h>  
#include <stdlib.h>
#include <string.h>

typedef struct {
    char pais[50];
    char estado[50];
    char bairro[50];
    char rua[50];
    char numero[50];
    char complemento[50];
    int cep[8];
} Endereco;

typedef struct {
    int ddd[2];
    int cep[8];
} Telefone;

typedef struct {
    int dia;
    int mes;
    int ano;
} Aniversario;

struct Pessoa{
    char nome[50];
    char email[50];
    Endereco endereco;
    Telefone telefone;
    Aniversario aniversario;
    
};

int main()
{
    struct Pessoa teste;
    Endereco teste2;

    strcpy(teste2.pais, "Brasil");

    strcpy(teste.nome, "Caderno");

    teste.endereco = teste2;

    printf("%s %s\n", teste.nome, teste.endereco.pais);

    return 0;
}

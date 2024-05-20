#include <stdio.h>  
#include <stdlib.h>
#include <string.h>

//Define o tipo endereço
typedef struct {
    char pais[50];
    char estado[50];
    char bairro[50];
    char rua[50];
    char numero[50];
    char complemento[50];
    char cep[9];
} Endereco;

//define o tipo telefone
typedef struct {
    char ddd[3];
    char num[9];
} Telefone;

//define o tipo aniversario
typedef struct {
    char dia[3];
    char mes[3];
    char ano[5];
} Aniversario;

//define pessoa, que recebe endereço, telefone e aniversario
typedef struct {
    char nome[50];
    char email[50];
    Endereco endereco;
    Telefone telefone;
    Aniversario aniversario;
    
} Pessoa;
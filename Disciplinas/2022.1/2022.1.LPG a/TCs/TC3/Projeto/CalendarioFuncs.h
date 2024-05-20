#include "CalendarioAUX.h"

Endereco inserirEndereco(){
    Endereco tempEnd;

    printf("Digite o Pais: ");
    scanf("%s",tempEnd.pais);
    printf("Digite o Estado: ");
    scanf("%s",tempEnd.estado);
    printf("Digite o Bairro: ");
    scanf("%s",tempEnd.bairro);
    printf("Digite a Rua: ");
    scanf("%s",tempEnd.rua);
    printf("Digite o Numero: ");
    scanf("%s",tempEnd.numero);
    printf("Digite o Complemento: ");
    scanf("%s",tempEnd.complemento);
    printf("Digite o Cep: ");
    scanf("%s",tempEnd.cep);

    return tempEnd;
}

Telefone inserirTelefone(){
    Telefone tempTel;

    printf("Digite o DDD: ");
    scanf("%s", tempTel.ddd);
    printf("Digite o Numero: ");
    scanf("%s",tempTel.num);

    return tempTel;
}

Aniversario inserirAniversario(){
    Aniversario tempAni;

    printf("Digite o Ano (0000 - 2022): ");
    scanf(" %s",tempAni.ano);
    printf("Digite o Mes (01 - 12): ");
    scanf(" %s",tempAni.mes);
    printf("Digite o Dia (01 - 31): ");
    scanf(" %s",tempAni.dia);

    return tempAni;
}


Pessoa inserirPessoa(Pessoa tempPessoa){
    char tempNome[50];
    char tempEmail[50];

    printf("Digite o Nome: ");
    scanf("%s",tempPessoa.nome);
    printf("Digite o Email: ");
    scanf("%s",tempPessoa.email);

    tempPessoa.aniversario = inserirAniversario();
    tempPessoa.telefone = inserirTelefone();
    tempPessoa.endereco = inserirEndereco();

    return tempPessoa;
}

void mostrarPessoas(Pessoa pessoa[], int max){
    int i;
    for (i = 0; i < max; i++){
        printf("%s - %s/%s/%s\n", pessoa[i].nome, pessoa[i].aniversario.dia, pessoa[i].aniversario.mes, pessoa[i].aniversario.ano);
    }
}

void buscarPessoasNomes(Pessoa pessoa[], int max){
    int i;
    char temp[50];

    printf("Digite o Nome que deseja procurar: ");
    scanf("%s",temp);

    for (i = 0; i < max; i++){
        if (strcmp(temp, pessoa[i].nome) == 0){
            printf("%s - %s - (%s) %s\n", pessoa[i].nome, pessoa[i].email, pessoa[i].telefone.ddd, pessoa[i].telefone.num);
        }
    }
}

void aniversariantesDoMes(Pessoa pessoa[], int max){
    int i;
    char temp[3];

    printf("Digite o Mes (01 - 12) que deseja procurar: ");
    scanf("%s",temp);

    for (i = 0; i < max; i++){
        if (strcmp(temp, pessoa[i].aniversario.mes) == 0){
            printf("%s - %s/%s/%s\n", pessoa[i].nome, pessoa[i].aniversario.dia, pessoa[i].aniversario.mes, pessoa[i].aniversario.ano);
        }
    }
}

void aniversariantesDoDia(Pessoa pessoa[], int max){
    int i;
    char temp[3];

    printf("Digite o Dia (01 - 31) que deseja procurar: ");
    scanf("%s",temp);

    for (i = 0; i < max; i++){
        if (strcmp(temp, pessoa[i].aniversario.dia) == 0){
            printf("%s - %s/%s/%s\n", pessoa[i].nome, pessoa[i].aniversario.dia, pessoa[i].aniversario.mes, pessoa[i].aniversario.ano);
        }
    }
}

Pessoa* trocarDoisItens(Pessoa pessoa[], int cod, int max){
    Pessoa temp;

    temp = pessoa[cod];
    pessoa[cod] = pessoa[max-1];
    pessoa[max-1] = temp;

    return pessoa;
}

Pessoa* deletarPessoa(Pessoa pessoa[], int max){
    int i, cod;

    for (i = 0; i < max; i++){
        printf("[%d] - %s\n", i, pessoa[i].nome);
    }

    printf("\nDigite o codigo ([COD] - nome) da Pessoa que deseja deletar: ");
    scanf("%d", &cod);

    return trocarDoisItens(pessoa, cod, max);
}

Pessoa* ordenarAlfabeticamente(Pessoa pessoa[], int max){
    Pessoa temp;
    int i, j;

    for(i=0; i<max; i++){
        for(j=0; j<max-1-i; j++){
            if(strcmp(pessoa[j].nome, pessoa[j+1].nome) > 0){
                //swap array[j] and array[j+1]
                temp = pessoa[j];
                pessoa[j] = pessoa[j+1];
                pessoa[j+1] = temp;
            }
        }
    }

    return pessoa;
}
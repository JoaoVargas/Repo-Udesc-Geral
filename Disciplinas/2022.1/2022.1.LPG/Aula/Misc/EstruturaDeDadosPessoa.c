struct Pessoa{
    int idade;
    char nome[50];
    float salario;
};

#include <stdio.h>

int main(){
    int i;
    struct Pessoa v[3];
    
    for( i = 0 ; i < 3 ; i++ ){
        printf("Pessoa %d:\n", i + 1);
        printf("Idade: ");
        scanf("%d", &v[i].idade);
        printf("Nome: ");
        scanf("%s", v[i].nome);
        printf("Salario: ");
        scanf("%f", &v[i].salario);
    }
    
    
    printf("\n");
    
    for( i = 0 ; i < 3 ; i++ ){
        printf("Dados da Pessoa %d:\n", i + 1);
        printf("Idade: %d\n", v[i].idade);
        printf("Nome: %s\n", v[i].nome);
        printf("Salario: R$%.2f\n\n", v[i].salario);
    }

    return 0;
}

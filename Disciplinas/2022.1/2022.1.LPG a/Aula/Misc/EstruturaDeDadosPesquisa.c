struct Pessoa{
    int idade;
    char sexo;
    float salario;
};

#include <stdio.h>
#define N 5

int main(){
    int i;
    int tempMulheres = 0;
    float tempIdade = 0, tempSal = 0;
    struct Pessoa v[N];
    
    for( i = 0 ; i < N ; i++ ){
        printf("Pessoa %d:\n", i + 1);
        printf("Idade: ");
        scanf("%d", &v[i].idade);
        printf("Sexo: ");
        scanf(" %c", &v[i].sexo);
        printf("Salario: ");
        scanf("%f", &v[i].salario);
    }
    
    
    printf("\n");
    
    for( i = 0 ; i < N ; i++ ){
        printf("Dados da Pessoa %d:\n", i + 1);
        printf("Idade: %d\n", v[i].idade);
        printf("Sexo: %c\n", v[i].sexo);
        printf("Salario: R$%.2f\n\n", v[i].salario);
    }
    
    for( i = 0 ; i < N ; i++ ){
        tempIdade += v[i].idade;
        tempSal += v[i].salario;
        if (v[i].sexo == 'F' && v[i].salario > 500){
            tempMulheres += 1;
        }
    }
    
    printf("Media Idade: %.2f\n", tempIdade/N);
    printf("Media Salarios: %.2f\n", tempSal/N);
    printf("Mulheres com salario acima de 500 : %d\n", tempMulheres);

    
    

    return 0;
}

#include <stdio.h>
 
int main() { 
    char nome;
    double fixo, vendas, total;

    scanf("%s %lf %lf", &nome, &fixo, &vendas);
    
    total = ((vendas * 15) / 100) + fixo;
    
    printf("TOTAL = R$ %.2f\n", total);

    return 0;
}
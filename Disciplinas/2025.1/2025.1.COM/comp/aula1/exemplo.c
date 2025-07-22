#include<stdio.h>
#define TAM 10

// Insere erro que é descoberto apenas na fase de ligação
// int f(int);

// Compilar com gcc --save-temps para ver arquivos intermediários

int main(int argc, char const *argv[])
{
    for (size_t i = 0; i < TAM; i++)
    {
        printf("Alo");
    }
    // int i = f(10);
    return 0;
}
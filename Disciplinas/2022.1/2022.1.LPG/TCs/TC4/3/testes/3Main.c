#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, num;
    int* linha;
    linha = malloc(sizeof(char) * 1);

    FILE *arqE = fopen("Entrada.txt", "rt"); //abre entrada
    if (arqE == NULL)
    {
        printf("Erro abrindo arquivo de entrada!\n");
        return 0;
    }

    while (!feof(arqE)) // loop principal
    {
        fscanf(arqE, "%d", &num);
        linha = realloc(linha, num * sizeof(char));
        printf("%d\n", num);

        for (i = 0; i < num; i++)
        {
            fscanf(arqE, "%d", &linha[i]);
            printf("%d ", linha[i]);
        }
        printf("\n");
        
    }
    
    fclose(arqE);
}
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, j, num;
    int** matriz;

    scanf("%d", &num);

    matriz = malloc(sizeof(int *) * num); 
    for (i = 0; i < num; i++)           
    {
        matriz[i] = malloc(sizeof(int) * num);
    }

    for (i = 0; i < num; i++)
    {
        for (j = 0; j < num; j++)
        {
            matriz[i][j] = i + j;
            printf("%3d", matriz[i][j]);
        }
        printf("\n");
    }

    free(matriz);
}

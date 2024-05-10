#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int **array, n;

void mostrarArray(void);
void movaAparaB(void);

int main()
{
    int i, j, menu = 1;
    size_t temp;

    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);

    array = malloc(sizeof(int *) * n);

    for (i = 0; i < n; i++)
    {
        array[i] = malloc(sizeof(int) * 1);
        //array[0][i] = 1;
        array[i][1] = i;
    }

    while (!menu == 0)
    {
        printf("--MENU--\n0 - Cancela\n1 - Mova A para B\n2- Mova A sobre B\n3 - Empilhe A sobre B\n");
        scanf("%d", &menu);

        switch (menu)
        {
        case 1:
            movaAparaB();
            mostrarArray();
            break;

        case 2:
            // movaAsobreB();
            break;

        case 3:
            // empilheAsobreB();
            break;

        default:
            break;
        }
    }
    return 0;
}

void mostrarArray()
{
    int i, j;
    size_t temp;
    printf("%d\n", n);

    for (i = 0; i < n; i++)
    {
        temp = sizeof(array[i]) / sizeof(int);

        for (j = 0; j < temp; j++)
        {
            printf("%d - array[%d][%d]   ", array[i][j], i, j);
        }
        printf("\n\n");
    }
}

void movaAparaB()
{
    int a, b, j;
    size_t tama, tamb;

    printf("Digite a posicao A:");
    scanf("%d", &a);
    printf("Digite a posicao B:");
    scanf("%d", &b);

    array[b] = realloc(array[b], (sizeof(array[a]) + sizeof(array[b])));

    tama = sizeof(array[a]) / sizeof(int);
    tamb = sizeof(array[b]) / sizeof(int);

    for (j = 1; j < tama; j++)
    {
        array[b][tamb + j] = array[a][j];
    }
}
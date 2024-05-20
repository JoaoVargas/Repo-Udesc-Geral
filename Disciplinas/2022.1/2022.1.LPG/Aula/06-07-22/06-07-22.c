#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int **cria_matriz(int *, int *);
void preenche_matriz(int **m, int, int);
void mostra_matriz(int **m, int, int);
void busca_maior_menor(int **m, int l, int c, int *lM, int *cM, int *lm, int *cm);
float media_matriz(int **m, int, int);
void libera_matriz(int **m, int, int);

int main()
{
    int l, c, lM, cM, lm, cm;
    int **matriz;

    matriz = cria_matriz(&l, &c);

    preenche_matriz(matriz, l, c);

    mostra_matriz(matriz, l, c);

    busca_maior_menor(matriz, l, c, &lM, &cM, &lm, &cm);

    printf("Posicao do maior: (%d,%d)\n", lM, cM);
    printf("Posicao do menor: (%d,%d)\n", lm, cm);
    printf("Media dos valores: %.3f\n", media_matriz(matriz, l, c));

    libera_matriz(matriz, l, c);
}

int **cria_matriz(int *linhas, int *colunas)
{
    int **m;
    int i;

    printf("Quantidade de linhas: ");
    scanf("%d", linhas);
    printf("Quantidade de colunas: ");
    scanf("%d", colunas);

    // garante que a quantidade de linhas e colunas seja válida
    if ((*linhas < 1) || (*colunas < 1))
    {
        printf("quantidade inválida!\n");
        return NULL;
    }

    m = malloc(*linhas * sizeof(int *));
    if (m == NULL)
    {
        printf("Não foi possível alocar linhas!\n");
        return NULL;
    }

    for (i = 0; i < *linhas; i++)
    {
        m[i] = malloc(*colunas * sizeof(int));
    }

    return m;
}

void preenche_matriz(int **m, int l, int c)
{
    int i, j;
    time_t t;
    srand((unsigned)time(&t));

    for (i = 0; i < l; i++)
        for (j = 0; j < c; j++)
            m[i][j] = rand() % 101;
}

void mostra_matriz(int **m, int l, int c)
{
    int i, j;

    printf("\nMatriz:\n");
    for (i = 0; i < l; i++)
    {
        for (j = 0; j < c; j++)
            printf("%4d", m[i][j]);
        printf("\n");
    }
}

void busca_maior_menor(int **m, int l, int c, int *lM, int *cM, int *lm, int *cm)
{
    int maior, menor, i, j;

    *lM = *cM = *lm = *cm = 0;
    maior = 0;
    menor = 100;

    for (i = 0; i < l; i++)
    {
        for (j = 0; j < c; j++)
        {
            if (m[i][j] > maior)
            {
                maior = m[i][j];
                *lM = i;
                *cM = j;
            }
            if (m[i][j] < menor)
            {
                menor = m[i][j];
                *lm = i;
                *cm = j;
            }
        }
    }
}

float media_matriz(int **m, int l, int c)
{
    int soma = 0;
    int i, j;

    for (i = 0; i < l; i++)
        for (j = 0; j < c; j++)
            soma += m[i][j];

    return (float)soma / (l * c);
}

void libera_matriz(int **m, int l, int c)
{
    int i;

    for (i = 0; i < l; i++)
    {
        free(m[i]);
    }
    free(m);
}
